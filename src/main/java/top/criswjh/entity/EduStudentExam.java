package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
     * 班级ID
     */
    private Long classId;

    /**
     * 批阅时间
     */
    private Date readTime;

    /**
     * 批阅人ID
     */
    private Long readId;

    /**
     * 状态：0未批阅、1已批阅
     */
    private Integer state;

    /**
     * 考试成绩
     */
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}