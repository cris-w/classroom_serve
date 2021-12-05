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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.util.JsonUtils;
import top.criswjh.util.JwtUtils;

/**
 * 退出成功处理类
 *
 * @author wjh
 * @date 2021/12/5 9:54 下午
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest req,
            HttpServletResponse res, Authentication authentication)
            throws IOException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, res, authentication);
        }

        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = res.getOutputStream();

        // 将请求头中 Authorization 清空
        res.setHeader(jwtUtils.getHead(), "");

        AjaxResult<Void> result = AjaxResult.success("退出成功");

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
