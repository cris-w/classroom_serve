package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.SysUserRole;
import top.criswjh.entity.vo.TeacherVo;
import top.criswjh.mapper.SysRoleMapper;
import top.criswjh.mapper.SysUserRoleMapper;
import top.criswjh.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import top.criswjh.service.SysUserService;

/**
 * @author wjh
 * @description 针对表【sys_user_role】的数据库操作Service实现
 * @createDate 2021-12-05 14:31:28
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysUserService sysUserService;

    @Override
    public List<SysRole> listRolesByUserId(Long id) {
        return sysRoleMapper.getUserRolesById(id);
    }

    @Override
    public List<TeacherVo> listUserByRoleName(String name) {
        List<Long> ids = sysUserRoleMapper.getUserIdsByRoleName(name);
        List<TeacherVo> userList = new ArrayList<>();
        ids.forEach(id -> {
            TeacherVo vo = new TeacherVo();
            SysUser user = sysUserService.getById(id);
            BeanUtils.copyProperties(user, vo);
            userList.add(vo);
        });
        return userList;
    }

    @Override
    @Cacheable(value = "hotTeacherList")
    public List<TeacherVo> listHotUser(String name) {
        List<Long> ids = sysUserRoleMapper.getHotUserIdsByRoleName(name);
        List<TeacherVo> userList = new ArrayList<>();
        ids.forEach(id -> {
            TeacherVo vo = new TeacherVo();
            SysUser user = sysUserService.getById(id);
            BeanUtils.copyProperties(user, vo);
            userList.add(vo);
        });
        return userList;
    }
}
