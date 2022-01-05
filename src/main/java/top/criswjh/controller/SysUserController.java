package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.SysUserRole;
import top.criswjh.entity.bo.PasswordBo;
import top.criswjh.entity.bo.UserAvatarBo;
import top.criswjh.service.SysUserService;

/**
 * @author wjh
 * @date 2021/12/29 8:39 PM
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 通过id获取用户信息，并将用户角色信息注入
     *
     * @param id id
     * @return user
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public AjaxResult<SysUser> info(@PathVariable Long id) {

        SysUser user = sysUserService.getById(id);
        Assert.notNull(user, "找不到该用户");

        // 通过用户id查询角色列表，用于回显
        List<SysRole> roleList = sysUserRoleService.listRolesByUserId(id);
        user.setRoleList(roleList);

        return AjaxResult.success(user);
    }

    /**
     * 模糊查询；通过名称查询用户列表
     *
     * @param username name
     * @return pageData
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public AjaxResult<Page<SysUser>> list(String username) {

        Page<SysUser> pageData = sysUserService.page(getPage(),
                new QueryWrapper<SysUser>().like(StrUtil.isNotBlank(username), "username",
                        username));

        // 将所有用户的角色查询出来并set进角色列表，用于页面展示
        pageData.getRecords().forEach(user -> {
            user.setRoleList(sysUserRoleService.listRolesByUserId(user.getId()));
        });
        return AjaxResult.success(pageData);
    }

    /**
     * 新增用户
     *
     * @param sysUser user
     * @return user
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public AjaxResult<SysUser> save(@Validated @RequestBody SysUser sysUser) {

        if (sysUserService.nameExist(sysUser.getUsername())) {
            return AjaxResult.error("用户名已存在", sysUser);
        }

        sysUser.setCreated(DateUtil.date());
        sysUser.setStatu(Const.STATUS_ON);

        // 默认密码 123456
        String pwd = passwordEncoder.encode(Const.DEFAULT_PASSWORD);
        sysUser.setPassword(pwd);

        // 默认头像
        sysUser.setAvatar(Const.DEFAULT_AVATAR);

        sysUserService.save(sysUser);
        return AjaxResult.success(sysUser);
    }

    /**
     * 更新用户
     *
     * @param sysUser user
     * @return user
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public AjaxResult<SysUser> update(@Validated @RequestBody SysUser sysUser) {

        SysUser old = sysUserService.getById(sysUser.getId());
        if (sysUserService.nameExist(sysUser.getUsername()) && !sysUser.getUsername()
                .equals(old.getUsername())) {
            return AjaxResult.error("用户名已存在", sysUser);
        }

        sysUser.setUpdated(DateUtil.date());
        sysUserService.updateById(sysUser);

        return AjaxResult.success(sysUser);
    }

    /**
     * 删除用户 同步删除相关中间表的信息
     *
     * @param userIds ids
     * @return ids
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional(rollbackFor = RuntimeException.class)
    public AjaxResult<Long[]> delete(@RequestBody Long[] userIds) {

        sysUserService.removeByIds(Arrays.asList(userIds));

        // 删除 user-role表 表相关信息
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id", userIds));

        return AjaxResult.success(userIds);
    }

    /**
     * 修改用户角色信息
     *
     * @param userId id
     * @return ""
     */
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('sys:user:role')")
    @Transactional(rollbackFor = RuntimeException.class)
    public AjaxResult<Void> rolePerm(@PathVariable Long userId, @RequestBody Long[] roleIds) {

        List<SysUserRole> userRoles = new ArrayList<>();

        Arrays.stream(roleIds).forEach(id -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(id);
            sysUserRole.setUserId(userId);

            userRoles.add(sysUserRole);
        });
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        sysUserRoleService.remove(wrapper.eq(SysUserRole::getUserId, userId));
        sysUserRoleService.saveBatch(userRoles);

        // 删除缓存
        sysUserService.clearUserAuthorityInfo(userId);

        return AjaxResult.success("修改用户角色成功");
    }

    /**
     * 重置用户密码
     *
     * @return "
     */
    @PostMapping("/repass")
    @PreAuthorize("hasAuthority('sys:user:repass')")
    public AjaxResult<Void> repass(@RequestBody Long userId) {

        SysUser user = sysUserService.getById(userId);

        user.setPassword(passwordEncoder.encode(Const.DEFAULT_PASSWORD));
        user.setUpdated(DateUtil.date());

        sysUserService.updateById(user);

        return AjaxResult.success("用户密码重置成功");
    }

    /**
     * 修改用户密码
     *
     * @return "
     */
    @PostMapping("/editPassword")
    public AjaxResult<Void> editPassword(@Validated @RequestBody PasswordBo passwordBo,
            Principal principal) {

        SysUser user = sysUserService.getUserByName(principal.getName());

        boolean matches = passwordEncoder.matches(passwordBo.getOldPassword(), user.getPassword());
        if (!matches) {
            return AjaxResult.error("原密码输入错误");
        }

        user.setPassword(passwordEncoder.encode(passwordBo.getNewPassword()));
        user.setUpdated(DateUtil.date());

        sysUserService.updateById(user);

        return AjaxResult.success("用户密码修改成功");
    }

    /**
     * 修改个人头像
     *
     * @param bo
     * @return
     */
    @PostMapping("/editAvatar")
    public AjaxResult<Void> editAvatar(@RequestBody UserAvatarBo bo) {

        SysUser user = sysUserService.getUserByName(bo.getUsername());
        user.setAvatar(bo.getAvatar());
        user.setUpdated(DateUtil.date());
        sysUserService.updateById(user);

        return AjaxResult.success("头像修改成功");
    }

    /**
     * 批量添加用户
     *
     * @param file
     * @return
     */
    @PostMapping("/addUserBatch")
    public AjaxResult<Void> addUserBatch(@RequestPart("file") MultipartFile file) {

        if (sysUserService.savaUserBatch(file, sysUserService)) {
            return AjaxResult.success("添加成功");
        }

        return AjaxResult.error("添加失败，部分用户名已存在");
    }
}
