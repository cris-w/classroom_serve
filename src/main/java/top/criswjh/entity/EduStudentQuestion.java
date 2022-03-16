package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生-试题关联表
 * @author wjh
 */
@TableName(value ="edu_student_question")
@Data
public class EduStudentQuestion implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 学生得分
     */
    private Integer score;

    /**
     * 学生答案
     */
    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}