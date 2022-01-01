package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.SysMenu;
import top.criswjh.entity.SysRoleMenu;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.dto.SysMenuDto;

/**
 * @author wjh
 * @date 2021/12/6 7:55 下午
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {


    /**
     * 获取当前用户的 菜单 和 权限信息
     *
     * @param principal
     * @return
     */
    @GetMapping("/nav")
    public AjaxResult<Map<Object, Object>> getNav(Principal principal) {
        SysUser user = sysUserService.getUserByName(principal.getName());

        // 获取权限信息
        String userAuthorityInfo = sysUserService.getUserAuthorityInfo(user.getId());
        String[] info = userAuthorityInfo.split(",");
        // 获取导航信息
        List<SysMenuDto> navs = sysMenuService.getCurrentUserNav();
        return AjaxResult.success(
                MapUtil.builder()
                        .put("authority", info)
                        .put("nav", navs)
                        .build());
    }

    /**
     * 通过id获取菜单信息
     *
     * @param id id
     * @return menu
     */
    @GetMapping("/info/{id}")
    @PreAuthorize(value = "hasAuthority('sys:menu:list')")
    public AjaxResult<SysMenu> info(@PathVariable Long id) {
        return AjaxResult.success(sysMenuService.getById(id));
    }

    /**
     * 获取菜单的树状结构
     *
     * @return menu-tree
     */
    @GetMapping("list")
    @PreAuthorize(value = "hasAuthority('sys:menu:list')")
    public AjaxResult<List<SysMenu>> list() {
        List<SysMenu> menus = sysMenuService.tree();
        return AjaxResult.success(menus);
    }

    /**
     * 新增菜单
     *
     * @param menu menu
     * @return 200
     */
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('sys:menu:save')")
    public AjaxResult<SysMenu> save(@Validated @RequestBody SysMenu menu) {
        if (sysMenuService.nameExist(menu.getName())) {
            return AjaxResult.error("菜单名不可以重复哦！", menu);
        } else {
            menu.setCreated(DateUtil.date());
            sysMenuService.save(menu);
            return AjaxResult.success(menu);
        }
    }

    /**
     * 更新菜单信息,并清除与更新的菜单有关的缓存
     *
     * @param menu menu
     * @return 200
     */
    @PostMapping("/update")
    @PreAuthorize(value = "hasAuthority('sys:menu:update')")
    public AjaxResult<SysMenu> update(@Validated @RequestBody SysMenu menu) {
        // 获得原数据
        SysMenu old = sysMenuService.getById(menu.getId());
        sysMenuService.removeById(menu.getId());
        if (sysMenuService.nameExist(menu.getName())) {
            sysMenuService.save(old);
            return AjaxResult.error("菜单名已存在！", menu);
        } else {
            menu.setUpdated(DateUtil.date());
            menu.setCreated(old.getCreated());
            sysMenuService.save(menu);

            // 清除所有与该菜单相关的权限缓存
            sysUserService.clearUserAuthorityInfoWhenMenuUpdate(menu.getId());
            return AjaxResult.success(menu);
        }
    }

    /**
     * 删除菜单，并清除与删除菜单有关的缓存，同时删除role-menu表相关记录
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize(value = "hasAuthority('sys:menu:delete')")
    public AjaxResult<Void> delete(@PathVariable Long id) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        long num = sysMenuService.count(wrapper.eq(SysMenu::getParentId, id));
        if(num > 0) {
            return AjaxResult.error("请先删除子菜单");
        }
        // 清除所有与该菜单相关的权限缓存
        sysUserService.clearUserAuthorityInfoWhenMenuUpdate(id);

        // 删除菜单，并删除角色-菜单与之相关的记录
        sysMenuService.removeById(id);
        LambdaQueryWrapper<SysRoleMenu> wrapper1 = new LambdaQueryWrapper<>();
        sysRoleMenuService.remove(wrapper1.eq(SysRoleMenu::getMenuId, id));

        return AjaxResult.success();
    }
}
