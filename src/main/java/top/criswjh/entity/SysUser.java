package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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

}