package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author wjh
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
@EqualsAndHashCode
@ToString
public class SysRole implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
     * 编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 状态
     */
    private Integer statu;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 关联的菜单ids
     */
    @TableField(exist = false)
    private List<Long> menuIds = new ArrayList<>();

}