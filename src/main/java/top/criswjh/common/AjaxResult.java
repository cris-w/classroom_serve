package top.criswjh.common;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wjh
 * @date 2021/11/29 10:41 下午
 */
@ToString
@EqualsAndHashCode
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AjaxResult<Void> success() {
        return success("操作成功");
    }

    public static <T> AjaxResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static AjaxResult<Void> success(String msg) {
        return success(msg, null);
    }

    public static <T> AjaxResult<T> success(String msg, T data) {
        return new AjaxResult(200, msg, data);
    }

    public Boolean isSuccess() {
        return 200 == this.code;
    }

    public static AjaxResult<Void> error() {
        return error("操作失败");
    }

    public static AjaxResult<Void> error(String msg) {
        return error(msg, null);
    }

    public static <T> AjaxResult<T> error(String msg, T data) {
        return new AjaxResult(500, msg, data);
    }

    public static AjaxResult<Void> error(int code, String msg) {
        return new AjaxResult(code, msg, (Object)null);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public AjaxResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public AjaxResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResult<T> setData(T data) {
        this.data = data;
        return this;
    }


    public AjaxResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
