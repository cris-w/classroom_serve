package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import top.criswjh.common.lang.Const;
import top.criswjh.common.redis.RedisCache;
import top.criswjh.entity.SysMenu;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;
import top.criswjh.mapper.SysRoleMapper;
import top.criswjh.mapper.SysRoleMenuMapper;
import top.criswjh.mapper.SysUserMapper;
import top.criswjh.mapper.SysUserRoleMapper;
import top.criswjh.service.SysMenuService;
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
    SysRoleMapper roleMapper;
    @Resource
    SysMenuService menuService;
    @Resource
    SysUserRoleMapper userRoleMapper;
    @Resource
    SysRoleMenuMapper roleMenuMapper;
    @Resource
    RedisCache redisCache;


    @Override
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        return userMapper.selectOne(wrapper.eq(SysUser::getUsername, username));
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
            List<SysRole> roles = roleMapper.getUserRoles(userId);
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
            redisCache.setCacheObject(Const.GRANTED_AUTHORITY + userId, authority, 1,
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
        Long roleId = roleMenuMapper.getRoleIdByMenuId(menuId);
        clearUserAuthorityInfoWhenRoleUpdate(roleId);
    }
}
