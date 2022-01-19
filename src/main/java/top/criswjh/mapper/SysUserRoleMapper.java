package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import top.criswjh.entity.SysUserRole;

/**
 * @author wjh
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 通过roleId 查询 所有含有 该roleId 的 userId
     *
     * @param roleId id
     * @return userIds
     */
    List<Long> getUserIdsByRoleId(Long roleId);

    /**
     * 通过roleName 查询所有含有该角色的 userId
     *
     * @param roleName name
     * @return userIds
     */
    List<Long> getUserIdsByRoleName(String roleName);

    /**
     * 通过roleName 查询前四含有该角色的 userId
     *
     * @param roleName
     * @return
     */
    List<Long> getHotUserIdsByRoleName(String roleName);
}
