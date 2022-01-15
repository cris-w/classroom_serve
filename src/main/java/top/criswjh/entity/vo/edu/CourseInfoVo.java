package top.criswjh.entity.vo.edu;

import lombok.Data;

/**
 * 课程信息Vo
 * 用于新增课程的回显
 *
 * @author wjh
 * @date 2022/1/6 7:05 PM
 */
@Data
public class CourseInfoVo {

    private Long id;
    private Long teacherId;
    private Long classId;
    private String title;
    private Integer lessonNum;
    private String cover;
    private String description;
}
