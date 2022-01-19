package top.criswjh.mapper;

import java.util.List;
import top.criswjh.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.criswjh.entity.vo.CoursePublishVo;
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

    /**
     * 根据课程ID，查询课程发布信息
     *
     * @param courseId
     * @return
     */
    CoursePublishVo getCoursePublishInfo(Long courseId);

    /**
     * 获取参加人数前8的课程
     *
     * @return
     */
    List<EduCourse> getHotList();
}




