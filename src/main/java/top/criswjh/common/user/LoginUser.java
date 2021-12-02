//package top.criswjh.common.user;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import java.util.Collection;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// * 登录用户身份权限
// *
// * @author wjh
// * @date 2021/12/2 9:17 下午
// */
//@Data
//@NoArgsConstructor
//@Accessors(chain = true)
//public class LoginUser implements UserDetails {
//
//    private static final long serialVersionUID = 1L;
//    /**
//     * 用户Id
//     */
//    private Long userId;
//
//    /**
//     * 本次登录id
//     */
//    @JsonIgnore
//    private String uuid;
//
//    /**
//     * 登录时间
//     */
//    private Long loginTime;
//
//    /**
//     * 过期时间
//     */
//    private Long expireTime;
//
//    /**
//     * 登录IP地址
//     */
//    private String ipaddr;
//
//    /**
//     * 浏览器类型
//     */
//    private String browser;
//
//    /**
//     * 操作系统
//     */
//    private String os;
//
//    /**
//     * 用户手机号
//     */
//    private String phoneNumber;
//
//    /**
//     * 昵称
//     */
//    private String nickName;
//
//
//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return "";
//    }
//
//    @JsonIgnore
//    @Override
//    public String getUsername() {
//        return phoneNumber;
//    }
//
//    /**
//     * 账户是否未过期,过期无法验证
//     */
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     * 指定用户是否解锁,锁定的用户无法进行身份验证
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    /**
//     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /**
//     * 是否可用 ,禁用的用户不能身份验证
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//}
