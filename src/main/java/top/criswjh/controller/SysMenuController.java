package top.criswjh.controller;

import cn.hutool.core.map.MapUtil;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
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
}
