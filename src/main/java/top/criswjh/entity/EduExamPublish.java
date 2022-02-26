package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发布考试表
 * @author wjh
 * @TableName edu_exam_publish
 */
@TableName(value ="edu_exam_publish")
@Data
public class EduExamPublish implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 试卷标题
     */
    private String paperTitle;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 限制时间：分钟
     */
    private Integer timeLimit;

    /**
     * 开始时间
     */
    private Date timeStart;

    /**
     * 结束时间
     */
    private Date timeEnd;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}