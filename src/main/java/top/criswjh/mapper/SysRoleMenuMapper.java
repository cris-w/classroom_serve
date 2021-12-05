package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.criswjh.entity.SysRoleMenu;

/**
* @author wjh
*/
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 通过menuId 查询 roleId
     * @param menuId
     * @return
     */
    Long getRoleIdByMenuId(Long menuId);

}
