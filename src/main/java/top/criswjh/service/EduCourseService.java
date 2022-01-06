package top.criswjh.service;

import top.criswjh.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.CourseInfoBo;

/**
* @author wjh
*/
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 创建课程
     *
     * @param courseInfo
     * @return courseId
     */
    Long saveCourseInfo(CourseInfoBo courseInfo);
}
