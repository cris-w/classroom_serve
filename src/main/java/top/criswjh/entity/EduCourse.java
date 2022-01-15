package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author wjh
 */
@TableName(value ="edu_course")
@Data
public class EduCourse implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 教师ID
     */
    private Long teacherId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 学科标题
     */
    private String title;

    /**
     * 课时数量
     */
    private Integer lessonNum;

    /**
     * 封面
     */
    private String cover;

    /**
     * 参加人数
     */
    private Integer joinCount;

    /**
     * 状态：0 未发布 1 已发布
     */
    private Integer status;

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