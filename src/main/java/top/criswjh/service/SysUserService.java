package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.SysUser;

/**
 * @author wjh
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过 username 获取 user
     *
     * @param username
     * @return user
     */
    SysUser getUserByName(String username);

    /**
     * 新增用户时，判断数据库是否存在该用户名
     *
     * @param username name
     * @return true 存在   false 不存在
     */
    boolean nameExist(String username);

    /**
     * 通过 id 获取 权限信息：ROLE_admin,Role_student,Role_teacher,sys:user:list.....
     *
     * @param userId id
     * @return authority
     */
    String getUserAuthorityInfo(Long userId);

    /**
     * 删除用户权限缓存
     *
     * @param userId userid
     */
    void clearUserAuthorityInfo(Long userId);

    /**
     * 当角色信息发生改变时，删除用户权限缓存
     *
     * @param roleId roleId
     */
    void clearUserAuthorityInfoWhenRoleUpdate(Long roleId);

    /**
     * 当菜单信息发生改变时，删除用户权限缓存
     *
     * @param menuId menuId
     */
    void clearUserAuthorityInfoWhenMenuUpdate(Long menuId);
}
