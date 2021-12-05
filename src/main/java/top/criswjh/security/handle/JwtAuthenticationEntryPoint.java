package top.criswjh.security.handle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.util.JsonUtils;

/**
 * jwt 认证入口
 *
 * @author wjh
 * @date 2021/12/5 1:25 下午
 */
@Component
public class JwtAuthenticationEntryPoint implements
        AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req,
            HttpServletResponse res, AuthenticationException e)
            throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        // 未认证 401
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = res.getOutputStream();

        AjaxResult<Void> result = AjaxResult.error(401,"请先登录");

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
