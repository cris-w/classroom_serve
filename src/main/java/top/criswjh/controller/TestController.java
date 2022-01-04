package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.Users;
import top.criswjh.service.UsersService;
import top.criswjh.util.OosUtil;

/**
 * @author wjh
 * @date 2021/11/29 6:43 下午
 */
@Api(tags = "测试模块")
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    UsersService usersService;
    @Resource
    OosUtil oosUtil;

    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "获取admin")
    @GetMapping("/getName")
    public String test() {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        return usersService.getOne(wrapper.eq(Users::getUsername, "admin")).getUsername();
    }

    @ApiOperation(value = "测试ajax post请求返回")
    @PostMapping("/testAjax")
    public AjaxResult<Users> testAjax(@RequestBody Users user){
        return AjaxResult.success(user);
    }

    @GetMapping("testoos")
    public AjaxResult testOos() {
        return AjaxResult.success(oosUtil.toString());
    }
}
