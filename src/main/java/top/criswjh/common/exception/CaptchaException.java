package top.criswjh.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author wjh
 * @date 2021/12/4 3:41 下午
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
