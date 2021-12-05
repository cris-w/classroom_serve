package top.criswjh.security.handle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.util.JsonUtils;
import top.criswjh.util.JwtUtils;

/**
 * 登录成功处理
 *
 * @author wjh
 * @date 2021/12/3 8:43 下午
 */
@Component
public class LoginSuccessHandler implements
        AuthenticationSuccessHandler {

    @Resource
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
            HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = res.getOutputStream();

        // 生成jwt，并放置到请求头中
        String jwt = jwtUtils.generateToken(authentication.getName());
        res.setHeader(jwtUtils.getHead(), jwt);

        AjaxResult<Void> result = AjaxResult.success("xxxx");

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
