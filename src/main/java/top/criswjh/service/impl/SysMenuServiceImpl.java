package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import top.criswjh.entity.SysMenu;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.dto.SysMenuDto;
import top.criswjh.mapper.SysMenuMapper;
import top.criswjh.mapper.SysUserMapper;
import top.criswjh.service.SysMenuService;
import org.springframework.stereotype.Service;
import top.criswjh.service.SysUserService;
import top.criswjh.util.JsonUtils;

/**
 * @author wjh
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2021-12-05 14:31:28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Resource
    SysUserService sysUserService;

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuDto> getCurrentUserNav() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser user = sysUserService.getUserByName(username);
        List<Long> menuIds = sysUserMapper.getNavMenuIds(user.getId());
        List<SysMenu> menus = this.listByIds(menuIds);

        // 转成树状结构
        List<SysMenu> menuTree = buildTreeMenu(menus);

        // 转成DTO
        return convert(menuTree);
    }

    @Override
    public List<SysMenu> tree() {
        // 获取所有菜单信息
        List<SysMenu> sysMenus = list(new QueryWrapper<SysMenu>().orderByAsc("orderNum"));

        // 转成树状结构
        return buildTreeMenu(sysMenus);
    }

    @Override
    public boolean nameExist(String name) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        return sysMenuMapper.selectCount(wrapper.eq(SysMenu::getName, name)) > 0;
    }

    @Override
    public boolean nameExistWhenEdit(String name) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        return sysMenuMapper.selectCount(wrapper.eq(SysMenu::getName, name)) > 1;
    }

    /**
     * 树形结构 -> SysMenuDto
     * @param menuTree
     * @return
     */
    private List<SysMenuDto> convert(List<SysMenu> menuTree) {
        ArrayList<SysMenuDto> menu = new ArrayList<>();
        menuTree.forEach(m -> {
            SysMenuDto dto = new SysMenuDto();
            dto.setId(m.getId());
            dto.setName(m.getPerms());
            dto.setTitle(m.getName());
            dto.setComponent(m.getComponent());
            dto.setPath(m.getPath());

            // 如果有字节点， 则递归调用该方法。
            if (m.getChildren().size() > 0) {
                dto.setChildren(convert(m.getChildren()));
            }

            menu.add(dto);
        });
        return menu;
    }

    /**
     *  menu对象 -> 树形结构
     * @param menus
     * @return
     */
    private List<SysMenu> buildTreeMenu(List<SysMenu> menus) {
        ArrayList<SysMenu> treeMenu = new ArrayList<>();
        for (SysMenu menu : menus) {
            for (SysMenu e : menus) {
                // 双重循环寻找所有节点的子节点
                if (menu.getId().equals(e.getParentId())) {
                    menu.getChildren().add(e);
                }
            }
            // 返回跟节点
            if (menu.getParentId() == 0L) {
                treeMenu.add(menu);
            }
        }
        System.out.println(JsonUtils.toJsonString(treeMenu));
        return treeMenu;
    }
}
