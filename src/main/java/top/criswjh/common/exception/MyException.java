package top.criswjh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wjh
 * @date 2022/1/5 10:02 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{

    private Integer code;
    private String msg;
}
