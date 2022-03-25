package top.criswjh.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.common.exception.MyException;
import top.criswjh.common.lang.Const;
import top.criswjh.common.redis.RedisCache;
import top.criswjh.entity.SysMenu;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.SysUserRole;
import top.criswjh.entity.dto.UserDto;
import top.criswjh.listener.UserExcelListener;
import top.criswjh.mapper.SysRoleMapper;
import top.criswjh.mapper.SysRoleMenuMapper;
import top.criswjh.mapper.SysUserMapper;
import top.criswjh.mapper.SysUserRoleMapper;
import top.criswjh.service.SysMenuService;
import top.criswjh.service.SysUserRoleService;
import top.criswjh.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysMenuService menuService;
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleMenuMapper roleMenuMapper;
    @Resource
    private RedisCache redisCache;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        return userMapper.selectOne(wrapper.eq(SysUser::getUsername, username));
    }

    @Override
    public boolean nameExist(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        return userMapper.selectCount(wrapper.eq(SysUser::getUsername, username)) > 0;
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {

        // 权限
        StringBuilder authority = new StringBuilder();

        // 先读缓存，如果不存在，则查询数据库
        if (redisCache.getCacheObject(Const.GRANTED_AUTHORITY + userId) != null) {
            String cache = redisCache.getCacheObject(Const.GRANTED_AUTHORITY + userId).toString();
            authority.append(cache);
        } else {
            // 获取权限编码
            List<SysRole> roles = roleMapper.getUserRolesById(userId);
            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode())
                        .collect(Collectors.joining(","));
                authority.append(roleCodes).append(",");
            }

            // 获取菜单编码
            List<Long> menuIds = userMapper.getNavMenuIds(userId);
            if (menuIds.size() > 0) {
                List<SysMenu> menus = menuService.listByIds(menuIds);
                String menu = menus.stream().map(SysMenu::getPerms)
                        .collect(Collectors.joining(","));
                authority.append(menu);
            }

            // 缓存到 redis 中，防止每次调用方法都要查询数据库获取权限
            redisCache.setCacheObject(Const.GRANTED_AUTHORITY + userId, authority.toString(), 1,
                    TimeUnit.HOURS);
        }

        return authority.toString();
    }

    @Override
    public void clearUserAuthorityInfo(Long userId) {
        redisCache.deleteObject(Const.GRANTED_AUTHORITY + userId);
    }

    @Override
    public void clearUserAuthorityInfoWhenRoleUpdate(Long roleId) {
        List<Long> ids = userRoleMapper.getUserIdsByRoleId(roleId);
        ids.forEach(this::clearUserAuthorityInfo);
    }

    @Override
    public void clearUserAuthorityInfoWhenMenuUpdate(Long menuId) {
        List<Long> roleIds = roleMenuMapper.getRoleIdByMenuId(menuId);
        roleIds.forEach(this::clearUserAuthorityInfoWhenRoleUpdate);
    }

    @Override
    @Transactional(rollbackFor = MyException.class)
    public boolean savaUserBatch(MultipartFile file, SysUserService sysUserService) {

        try {
            // 文件输入流
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, UserDto.class,
                            new UserExcelListener(sysUserService, passwordEncoder))
                    .sheet()
                    .doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean defaultRole(List<SysUser> list) {
        List<SysUserRole> collect = list.stream().map(l -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(l.getId());
            sysUserRole.setRoleId(3L);
            return sysUserRole;
        }).collect(Collectors.toList());
        return sysUserRoleService.saveBatch(collect);
    }
}
