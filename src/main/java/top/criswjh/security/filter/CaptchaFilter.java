package top.criswjh.security.filter;

import cn.hutool.core.util.StrUtil;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.criswjh.common.exception.CaptchaException;
import top.criswjh.common.lang.Const;
import top.criswjh.common.redis.RedisCache;
import top.criswjh.security.handle.LoginFailureHandler;

/**
 * 验证码过滤器
 *
 * @author wjh
 * @date 2021/12/3 9:05 下午
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    RedisCache redisCache;
    @Resource
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        // 获取url 并判断是否为登录的请求
        String url = req.getRequestURI();
        if (Const.LOGIN_URI.equals(url) && Const.LOGIN_METHOD.equals(req.getMethod())) {
            try {
                validate(req);
            } catch (CaptchaException e) {
                // 将失败信息交给失败处理器
                loginFailureHandler.onAuthenticationFailure(req, res, e);
            }
        }
        filterChain.doFilter(req, res);
    }

    /**
     * 校验验证码逻辑
     *
     * @param req req
     */
    private void validate(HttpServletRequest req) {

        String code = req.getParameter("code");
        String key = req.getParameter("token");

        if (StrUtil.isEmpty(code) || StrUtil.isEmpty(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisCache.getCacheObject(key))) {
            throw new CaptchaException("验证码错误");
        }

        // 一次性使用
        redisCache.deleteObject(key);
    }
}
