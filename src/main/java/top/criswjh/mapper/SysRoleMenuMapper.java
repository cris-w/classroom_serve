package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import top.criswjh.entity.SysRoleMenu;

/**
* @author wjh
*/
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 通过menuId 查询 roleIds
     * @param menuId
     * @return
     */
    List<Long> getRoleIdByMenuId(Long menuId);

}
