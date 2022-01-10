package top.criswjh.entity.bo.sys;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author wjh
 * @date 2021/12/29 10:54 PM
 */
@Data
public class PasswordBo {
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
