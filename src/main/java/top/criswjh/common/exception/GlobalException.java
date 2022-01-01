package top.criswjh.common.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
     * 表单实体检验异常
     * @param e e
     * @return error 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult<Void> handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        log.error("实体检验异常-----------------{}", objectError.getDefaultMessage());
        return AjaxResult.error(400, objectError.getDefaultMessage());
    }

    /**
     * jwt异常
     *
     * @param e e
     * @return error 50014
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = JwtException.class)
    public AjaxResult<Void> handler(JwtException e) {
        log.error("jwt异常-----------------{}", e.getMessage());
        return AjaxResult.error(50014, e.getMessage());
    }

    /**
     * 非法参数异常
     *
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
