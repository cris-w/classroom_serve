package top.criswjh.security.filter;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.criswjh.util.JwtUtils;

/**
 * @author wjh
 * @date 2021/12/5 1:50 上午
 */
@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    JwtUtils jwtUtils;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtils.getHead());
        // 如果jwt 为空，则跳出，被后面的过滤器拦截
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        Claims claims = jwtUtils.getClaimByToken(jwt);
        if (claims == null) {
            throw new JwtException("token 为空");
        }

        if (jwtUtils.isTokenExpire(claims)) {
            throw new JwtException("token 过期");
        }

        String username = claims.getSubject();
        // 获取用户权限信息

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username, null, null);
        // 让security 完成用户登录
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}
