package top.criswjh;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.Users;
import top.criswjh.mapper.SysUserMapper;
import top.criswjh.mapper.UsersMapper;

@SpringBootTest
@MapperScan("top.criswjh.mapper")
class ClassroomServeApplicationTests {
    @Resource
    private SysUserMapper mapper;

    @Test
    public void test() {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        System.out.println(mapper.selectOne(wrapper.eq(SysUser::getUsername, "admin")).getPassword());
    }

}
