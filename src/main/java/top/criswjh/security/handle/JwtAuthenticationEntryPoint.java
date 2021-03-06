package top.criswjh.security.handle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.util.JsonUtils;

/**
 * jwt 认证入口
 *
 * @author wjh
 * @date 2021/12/5 1:25 下午
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements
        AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req,
            HttpServletResponse res, AuthenticationException e)
            throws IOException {
        res.setContentType(Const.REQUEST_HEADERS_CONTENT_TYPE);
        // 未认证 401
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = res.getOutputStream();
        log.warn("400000000001");
        AjaxResult<Void> result = AjaxResult.error(401,"请先登录");

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
