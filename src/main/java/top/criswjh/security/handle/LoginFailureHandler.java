package top.criswjh.security.handle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.util.JsonUtils;

/**
 * 登录失败处理
 *
 * @author wjh
 * @date 2021/12/3 8:43 下午
 */
@Component
public class LoginFailureHandler implements
        AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req,
            HttpServletResponse res, AuthenticationException e)
            throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = res.getOutputStream();

        AjaxResult<Void> result = AjaxResult.error(
                Const.BAD_CREDENTIALS.equals(e.getMessage()) ? "用户名密码错误" : e.getMessage());

        outputStream.write(Objects.requireNonNull(JsonUtils.toJsonString(result))
                .getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
