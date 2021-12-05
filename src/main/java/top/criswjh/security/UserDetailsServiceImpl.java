package top.criswjh.security;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.criswjh.entity.SysUser;
import top.criswjh.security.dto.LoginUser;
import top.criswjh.service.SysUserService;

/**
 * @author wjh
 * @date 2021/11/29 10:12 下午
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 使用mybatis plus 查询用户
        SysUser user = sysUserService.getUserByName(name);

        // 如果对象为空，抛出异常
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else {
            // 返回User对象。
            return new LoginUser(user.getId(), user.getUsername(), user.getPassword(), getUserAuthority(user.getId()));
        }
    }

    /**
     * 通过 userId 获取 权限(角色、 菜单权限)
     * @param userId id
     * @return 权限列表
     */
    public List<GrantedAuthority> getUserAuthority(Long userId) {
        // 角色
        String userAuthorityInfo = sysUserService.getUserAuthorityInfo(userId);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(userAuthorityInfo);
    }
}
