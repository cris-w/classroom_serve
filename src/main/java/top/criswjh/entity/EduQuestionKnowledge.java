package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 题目-知识点关联表
 * @author wjh
 * @TableName edu_question_knowledge
 */
@TableName(value ="edu_question_knowledge")
@Data
public class EduQuestionKnowledge implements Serializable {
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
     * 知识点ID
     */
    private Long knowledgeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}