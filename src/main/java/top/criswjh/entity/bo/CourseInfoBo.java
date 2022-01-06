package top.criswjh.entity.bo;

import lombok.Data;

/**
 * @author wjh
 * @date 2022/1/6 7:05 PM
 */
@Data
public class CourseInfoBo {

    private Long teacherId;
    private Long classId;
    private String title;
    private Integer lessonNum;
    private String cover;
    private String description;
}
