package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.SysUserRole;
import top.criswjh.entity.vo.TeacherVo;

/**
 * @author wjh
 * @description 针对表【sys_user_role】的数据库操作Service
 * @createDate 2021-12-05 14:31:28
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 通过用户id 查询该用户的 角色信息
     *
     * @param id
     * @return
     */
    List<SysRole> listRolesByUserId(Long id);

    /**
     * 通过角色名查询含有该角色的用户
     *
     * @param name name
     * @return ids
     */
    List<TeacherVo> listUserByRoleName(String name);

    /**
     * 通过角色名查询前四的用户
     *
     * @param name
     * @return
     */
    List<TeacherVo> listHotUser(String name);

}
