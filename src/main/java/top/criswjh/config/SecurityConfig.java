package top.criswjh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置类
 * @author wjh
 * @date 2021/11/29 10:09 下午
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义编写的登陆页面
        http.formLogin()
                // 登陆页面设置
                .loginPage("/login.html")
                // 设置登陆访问路径
                .loginProcessingUrl("/user/login")
                // 登陆成功后跳转的路径
                .defaultSuccessUrl("/test/index").permitAll()
                .and().authorizeRequests()
                // 设置可以放行的路径，不需要认证
                .antMatchers("/", "/user/login").permitAll()
                .anyRequest().authenticated()
                // 关闭csrf防护
                .and().csrf().disable();
    }
}
