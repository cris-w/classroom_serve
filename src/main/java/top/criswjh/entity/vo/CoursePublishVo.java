package top.criswjh.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程发布信息Vo
 *
 * @author wjh
 * @date 2022/1/15 12:24 AM
 */
@Data
public class CoursePublishVo {

    /**
     * 课程ID
     */
    private Long id;
    private String title;
    private String cover;
    private String lessonNum;
    private String description;
    /**
     * 教师名
     */
    private String username;
    /**
     * 班级名
     */
    @TableField("class_title")
    private String classTitle;

}
