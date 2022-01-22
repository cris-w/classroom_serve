package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题库表
 * @author wjh
 */
@TableName(value ="edu_question_bank")
@Data
public class EduQuestionBank implements Serializable {
    /**
     * 题库唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目描述
     */
    private String title;

    /**
     * 选择题的选项
     */
    private String option;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目类型：0单选，1多选，2天空，3主观
     */
    private Integer type;

    /**
     * 题目等级：0简单，1中等，2困难
     */
    private Integer level;

    /**
     * 关联的知识点ID，多个ID通过，隔开
     */
    private String knowledge;

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