package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 考试-试卷表
 * @author wjh
 */
@TableName(value ="edu_exam_paper")
@Data
public class EduExamPaper implements Serializable {
    /**
     * 试卷唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 老师id
     */
    private Long teacherId;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 试卷描述
     */
    private String description;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 考试时间限制：单位分钟
     */
    private Integer timeLimit;

    /**
     * 考试开始时间
     */
    private Date timeStart;

    /**
     * 考试结束时间
     */
    private Date timeEnd;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 跟新时间
     */
    private Date gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}