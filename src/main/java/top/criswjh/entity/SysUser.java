package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * username
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * pwd
     */
    private String password;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * email
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 城市
     */
    private String city;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 最后登录时间
     */
    private Date lastLogin;

    /**
     * 状态
     */
    private Integer statu;

    /**
     * 用户角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<>();

}