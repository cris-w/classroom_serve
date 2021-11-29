package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.entity.Users;
import top.criswjh.service.UsersService;

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

    @ApiOperation(value = "获取admin")
    @GetMapping("/getName")
    public String test() {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        return usersService.getOne(wrapper.eq(Users::getUsername, "admin")).getUsername();
    }
}
