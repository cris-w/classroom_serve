package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import top.criswjh.entity.SysUserRole;

/**
 * @author wjh
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 通过roldId 查询 所有含有改 roleId 的 userId
     * @param roleId
     * @return
     */
    List<Long> getUserIdsByRoleId (Long roleId);
}
