package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生-试卷关联表
 * @author wjh
 */
@TableName(value ="edu_student_exam")
@Data
public class EduStudentExam implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 状态：0未完成、1已完成、2已过期
     */
    private Integer state;

    /**
     * 成绩
     */
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}