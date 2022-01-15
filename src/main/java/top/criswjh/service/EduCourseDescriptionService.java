package top.criswjh.service;

import top.criswjh.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
*/
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    /**
     * 通过课程ID 删除描述
     *
     * @param courseId
     */
    void removeByCourseId(Long courseId);
}
