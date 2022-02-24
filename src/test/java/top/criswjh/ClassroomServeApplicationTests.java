package top.criswjh;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.criswjh.entity.SysUser;
import top.criswjh.mapper.SysUserMapper;

@SpringBootTest
class ClassroomServeApplicationTests {
    @Resource
    private SysUserMapper mapper;

    @Test
    public void test() {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        Date admin = mapper.selectOne(wrapper.eq(SysUser::getUsername, "admin")).getCreated();
        DateTime dateTime = new DateTime(admin);
        System.out.println(admin);
        System.out.println(dateTime);
    }

}
