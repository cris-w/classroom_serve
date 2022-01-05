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
 */
@TableName(value ="edu_subject")
@Data
public class EduSubject implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程名称
     */
    private String title;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}