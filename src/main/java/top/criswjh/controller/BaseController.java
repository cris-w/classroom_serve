package top.criswjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import top.criswjh.common.redis.RedisCache;
import top.criswjh.service.SysMenuService;
import top.criswjh.service.SysRoleMenuService;
import top.criswjh.service.SysRoleService;
import top.criswjh.service.SysUserRoleService;
import top.criswjh.service.SysUserService;

/**
 * @author wjh
 * @date 2021/12/3 6:37 下午
 */
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisCache redisCache;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

}
