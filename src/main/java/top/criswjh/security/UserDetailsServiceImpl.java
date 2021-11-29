package top.criswjh.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.criswjh.entity.Users;
import top.criswjh.mapper.UsersMapper;

/**
 * @author wjh
 * @date 2021/11/29 10:12 下午
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UsersMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 使用mybatis plus 查询用户
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, name);
        // 查询到的用户对象
        Users user = userMapper.selectOne(wrapper);

        // 如果对象为空，抛出异常
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            // 返回User对象。
            List<GrantedAuthority> auths =
                    AuthorityUtils.commaSeparatedStringToAuthorityList("user,ROLE_admin");
            return new User(user.getUsername(),
                    new BCryptPasswordEncoder().encode(user.getPassword()), auths);
        }
    }
}
