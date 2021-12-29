package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import top.criswjh.entity.SysRole;

/**
* @author wjh
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2021-12-05 14:31:28
* @Entity generate.domain.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过 userID 茶查询 role
     * @param userId
     * @return
     */
    List<SysRole> getUserRolesById(Long userId);

}
