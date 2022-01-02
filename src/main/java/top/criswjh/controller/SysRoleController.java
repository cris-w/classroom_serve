package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.Name;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysRoleMenu;
import top.criswjh.entity.SysUserRole;

/**
 * 角色控制层
 *
 * @author wjh
 * @date 2021/12/29 4:15 PM
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    /**
     * 通过id获取角色信息
     *
     * @param id id
     * @return sysRole
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public AjaxResult<SysRole> info(@PathVariable Long id) {
        // 通过id查询出 role详细信息
        SysRole role = sysRoleService.getById(id);

        // 通过 id 在 role-menu表中查询出所有关联菜单id
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(
                wrapper.eq(SysRoleMenu::getRoleId, id));
        List<Long> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId)
                .collect(Collectors.toList());
        role.setMenuIds(menuIds);

        return AjaxResult.success(role);
    }

    /**
     * 模糊查询；通过名称查询角色列表
     *
     * @param name
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public AjaxResult<Page<SysRole>> list(String name) {

        Page<SysRole> pageData = sysRoleService.page(getPage(),
                new QueryWrapper<SysRole>().like(StrUtil.isNotBlank(name), "name", name));

        return AjaxResult.success(pageData);
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public AjaxResult<SysRole> save(@Validated @RequestBody SysRole sysRole) {

        if (sysRoleService.nameExist(sysRole.getName())) {
            return AjaxResult.error("角色名已存在", sysRole);
        }

        if (sysRoleService.codeExist(sysRole.getCode())) {
            return AjaxResult.error("唯一编码已存在", sysRole);
        }
        sysRole.setCreated(DateUtil.date());
        sysRole.setStatu(Const.STATUS_ON);

        sysRoleService.save(sysRole);
        return AjaxResult.success(sysRole);
    }

    /**
     * 更新角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public AjaxResult<SysRole> update(@Validated @RequestBody SysRole sysRole) {

        // 原数据
        SysRole old = sysRoleService.getById(sysRole.getId());

        if (sysRoleService.nameExist(sysRole.getName()) && !old.getName().equals(sysRole.getName())) {
            return AjaxResult.error("角色名已存在", sysRole);
        }
        if (sysRoleService.codeExist(sysRole.getCode()) && !old.getCode().equals(sysRole.getCode())) {
            return AjaxResult.error("唯一编码已存在", sysRole);
        }

        sysRole.setUpdated(DateUtil.date());
        sysRoleService.updateById(sysRole);
        // 更新缓存
        sysUserService.clearUserAuthorityInfoWhenRoleUpdate(sysRole.getId());

        return AjaxResult.success(sysRole);
    }

    /**
     * 删除角色 同步删除相关中间表的信息
     *
     * @param roleIds
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional(rollbackFor = RuntimeException.class)
    public AjaxResult<Long[]> delete(@RequestBody Long[] roleIds) {

        sysRoleService.removeByIds(Arrays.asList(roleIds));

        // 删除 user-role表 以及 role-menu 表相关信息
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("role_id", roleIds));
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().in("role_id", roleIds));

        // 缓存同步删除
        Arrays.stream(roleIds).forEach(id -> {
            sysUserService.clearUserAuthorityInfoWhenRoleUpdate(id);
        });

        return AjaxResult.success(roleIds);
    }

    /**
     * 修改角色权限信息
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/perm/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:perm')")
    @Transactional(rollbackFor = RuntimeException.class)
    public AjaxResult<Void> perm(@PathVariable Long roleId, @RequestBody Long[] menuIds) {

        List<SysRoleMenu> roleMenusList = new ArrayList<>();

        Arrays.stream(menuIds).forEach(id -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(id);
            sysRoleMenu.setRoleId(roleId);

            roleMenusList.add(sysRoleMenu);
        });

        // 先删除原来的记录，再保存新的
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        sysRoleMenuService.saveBatch(roleMenusList);

        // 删除缓存信息
        sysUserService.clearUserAuthorityInfoWhenRoleUpdate(roleId);

        return AjaxResult.success("修改角色权限成功");
    }
}
