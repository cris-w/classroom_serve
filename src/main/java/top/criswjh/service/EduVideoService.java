package top.criswjh.service;

import top.criswjh.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
*/
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 通过课程ID 删除小节信息
     *
     * @param courseId
     */
    void removeByCourseId(Long courseId);

    /**
     * 删除小节以及小节视屏
     *
     * @param videoId
     */
    void removeVideo(Long videoId);
}
