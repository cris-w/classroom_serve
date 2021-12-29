package top.criswjh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
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
    HttpServletRequest req;

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

    /**
     * 使用mybatis-plus 插件 获取当前页码
     * @return page
     */
    public Page getPage() {
        int current = ServletRequestUtils.getIntParameter(req, "current", 1);
        int size = ServletRequestUtils.getIntParameter(req, "size", 1);

        return new Page(current, size);
    }

}
