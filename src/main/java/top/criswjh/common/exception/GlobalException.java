package top.criswjh.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.criswjh.common.lang.AjaxResult;

/**
 * 全局异常处理类
 *
 * @author wjh
 * @date 2021/12/5 2:51 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {


    /**
     * 非法参数异常
     * @param e e
     * @return error 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public AjaxResult<Void> handler(IllegalArgumentException e) {
        log.error("Assert异常-----------------{}", e.getMessage());
        return AjaxResult.error(400, e.getMessage());
    }

    /**
     * 运行时异常捕获
     * @param e e
     * @return error 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public AjaxResult<Void> handler(RuntimeException e) {
        log.error("运行时异常-----------------{}", e.getMessage());
        return AjaxResult.error(400, e.getMessage());
    }
}
