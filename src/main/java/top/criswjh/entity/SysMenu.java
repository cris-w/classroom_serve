package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    private Long parentId;

    /**
     *
     */
    private String name;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     *
     */
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer ordernum;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysMenu other = (SysMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getParentId() == null ? other.getParentId() == null
                : this.getParentId().equals(other.getParentId()))
                && (this.getName() == null ? other.getName() == null
                : this.getName().equals(other.getName()))
                && (this.getPath() == null ? other.getPath() == null
                : this.getPath().equals(other.getPath()))
                && (this.getPerms() == null ? other.getPerms() == null
                : this.getPerms().equals(other.getPerms()))
                && (this.getComponent() == null ? other.getComponent() == null
                : this.getComponent().equals(other.getComponent()))
                && (this.getType() == null ? other.getType() == null
                : this.getType().equals(other.getType()))
                && (this.getIcon() == null ? other.getIcon() == null
                : this.getIcon().equals(other.getIcon()))
                && (this.getOrdernum() == null ? other.getOrdernum() == null
                : this.getOrdernum().equals(other.getOrdernum()))
                && (this.getCreated() == null ? other.getCreated() == null
                : this.getCreated().equals(other.getCreated()))
                && (this.getUpdated() == null ? other.getUpdated() == null
                : this.getUpdated().equals(other.getUpdated()))
                && (this.getStatu() == null ? other.getStatu() == null
                : this.getStatu().equals(other.getStatu()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getPerms() == null) ? 0 : getPerms().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getStatu() == null) ? 0 : getStatu().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", path=").append(path);
        sb.append(", perms=").append(perms);
        sb.append(", component=").append(component);
        sb.append(", type=").append(type);
        sb.append(", icon=").append(icon);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", statu=").append(statu);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}