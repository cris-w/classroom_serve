package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生-视频关联表
 * @author wjh
 * @TableName edu_student_video
 */
@TableName(value ="edu_student_video")
@Data
public class EduStudentVideo implements Serializable {
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
     * 视屏ID
     */
    private Long videoId;

    /**
     * 课程ID
     */
    private Long courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}