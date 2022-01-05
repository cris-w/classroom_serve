package top.criswjh.security.handle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.util.JsonUtils;

/**
 * 拒绝访问处理器
 *
 * @author wjh
 * @date 2021/12/5 1:27 下午
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req,
            HttpServletResponse res, AccessDeniedException e)
            throws IOException {
        res.setContentType(Const.REQUEST_HEADERS_CONTENT_TYPE);
         // 无权 403
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ServletOutputStream outputStream = res.getOutputStream();

        AjaxResult<Void> result = AjaxResult.error(403, e.getMessage());

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
