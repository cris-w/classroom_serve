package top.criswjh.controller;

import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.vo.TeacherVo;
import top.criswjh.service.SysUserRoleService;

/**
 * @author wjh
 * @date 2022/1/7 1:36 AM
 */
@Api(tags = "用户角色管理模块")
@RestController
@RequestMapping("/sys/userRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/getTeacherList")
    public AjaxResult<List<TeacherVo>> getTeacherList() {

        List<TeacherVo> list = sysUserRoleService.listUserByRoleName(Const.TEACHER_ROLE);
        return AjaxResult.success(list);
    }
}
