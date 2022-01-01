package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import top.criswjh.entity.SysMenu;
import top.criswjh.entity.dto.SysMenuDto;

/**
 * @author wjh
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2021-12-05 14:31:28
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取当前用户能管理的菜单列表
     * @return list
     */
    List<SysMenuDto> getCurrentUserNav();

    /**
     * 返回menu的树状结构
     * @return menu-tree
     */
    List<SysMenu> tree();

    /**
     * 判断插入时name字段是否存在
     *
     * @param name name
     * @return true 存在  false 不存在
     */
    boolean nameExist(String name);

    /**
     * 判断更新时name字段是否存在
     * @param name name
     * @return true 存在  false 不存在
     */
    boolean nameExistWhenEdit(String name);
}
