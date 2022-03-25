package top.criswjh.config;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.criswjh.security.filter.CaptchaFilter;
import top.criswjh.security.filter.JwtAuthenticationFilter;
import top.criswjh.security.handle.JwtAccessDeniedHandler;
import top.criswjh.security.handle.JwtAuthenticationEntryPoint;
import top.criswjh.security.handle.JwtLogoutSuccessHandler;
import top.criswjh.security.handle.LoginFailureHandler;
import top.criswjh.security.handle.LoginSuccessHandler;

/**
 * spring security 配置类
 *
 * @author wjh
 * @date 2021/11/29 10:09 下午
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 白名单
     */
    private static final String[] URL_WHITELIST = {
            "/",
            "/doc.html",
            "/v2/**",
            "/webjars/**",
            "/favicon.ico",
            "/swagger-resources/**",
            "/login",
            "/logout",
            "/auth/captcha",
            "/sys/user/register"
    };

    /**
     * 自定义用户认证逻辑
     */
    @Resource
    private UserDetailsService userDetailsService;
    /**
     * 自定义登录失败处理
     */
    @Resource
    private LoginFailureHandler loginFailureHandler;
    /**
     * 自定义登录成功处理
     */
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    /**
     * 验证码过滤器
     */
    @Resource
    private CaptchaFilter captchaFilter;
    /**
     * 成功退出处理器
     */
    @Resource
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    /**
     * Jwt认证入口
     */
    @Resource
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    /**
     * 拒绝访问处理器
     */
    @Resource
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义编写的登陆页面
        http.formLogin()
                // 登录配置
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)

                // 登出配置
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)

                // 禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 配置拦截规则
                .and()
                .authorizeRequests()
                // 设置可以放行的路径，不需要认证
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()

                // 异常处理
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 配置自定义过滤器
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors()
                // 关闭csrf防护
                .and()
                .csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return 
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
