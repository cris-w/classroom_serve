package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 知识点表
 * @author wjh
 */
@TableName(value ="edu_knowledge_point")
@Data
public class EduKnowledgePoint implements Serializable {
    /**
     * 知识点唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父ID：0表示无父ID
     */
    private Long parentId;

    /**
     * 知识点名称
     */
    private String title;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}