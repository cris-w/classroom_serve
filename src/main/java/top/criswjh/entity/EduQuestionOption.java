package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目-选项关联表（单选/多选）
 * @author wjh
 */
@TableName(value ="edu_question_option")
@Data
public class EduQuestionOption implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 选项
     */
    @TableField("`option`")
    private String option;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}