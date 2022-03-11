package top.criswjh.entity.vo.course;

import lombok.Data;

/**
 *
 * 用于回显课程的详细信息
 * 将id 转为 name
 *
 * @author wjh
 * @date 2022/3/10 5:05 PM
 */
@Data
public class CourseVo {
    private Long id;
    private String teacherName;
    private String className;
    private String title;
    private String cover;
    private Integer status;
}
