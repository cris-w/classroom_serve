package top.criswjh.common.security.handle;

import cn.hutool.http.HttpStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.criswjh.common.AjaxResult;
import top.criswjh.util.JsonUtils;
import top.criswjh.util.ServletUtils;

/**
 * @author wjh
 * @date 2021/12/2 9:16 下午
 */
//public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
//    @Autowired
//    private TokenService tokenService;
//
//    /**
//     * 退出处理
//     */
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//            Authentication authentication)
//            throws IOException, ServletException {
//        /*LoginUser loginUser = tokenService.getLoginUser(request);
//        if (Validator.isNotNull(loginUser)) {
//            String userName = loginUser.getUsername();
//            // 删除用户缓存记录
//            tokenService.delLoginUser(loginUser);
//        }*/
//        ServletUtils.renderString(response,
//                JsonUtils.toJsonString(AjaxResult.error(HttpStatus.HTTP_OK, "退出成功")));
//    }
//}
