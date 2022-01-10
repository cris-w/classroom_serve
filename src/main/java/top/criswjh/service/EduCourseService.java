package top.criswjh.service;

import top.criswjh.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.CourseInfoBo;
import top.criswjh.entity.vo.edu.CourseInfoVo;

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

    /**
     * 通过Id获取 课程信息
     *
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfoById(Long courseId);
    
    /**
     * 更新课程信息
     *
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
