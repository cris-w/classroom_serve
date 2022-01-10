package top.criswjh.mapper;

import top.criswjh.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.criswjh.entity.vo.edu.CourseInfoVo;

/**
* @author wjh
*/
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 通过ID 获取课程信息
     *
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(Long courseId);
}




