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
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author wjh
 * @TableName sys_menu
 */
@TableName(value = "sys_menu")
@Data
public class SysMenu implements Serializable {

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @NotNull(message = "上级菜单不能为空")
    private Long parentId;

    /**
     * 名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @NotBlank(message = "菜单授权码不能为空")
    private String perms;

    /**
     *
     */
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    @TableField("orderNum")
    private Integer orderNum;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更行时间
     */
    private Date updated;

    /**
     *
     */
    private Integer statu;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();
}