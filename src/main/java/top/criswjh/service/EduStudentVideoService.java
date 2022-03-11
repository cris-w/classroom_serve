package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import top.criswjh.entity.EduStudentVideo;

/**
* @author wjh
* @description 针对表【edu_student_video(学生-视频关联表)】的数据库操作Service
* @createDate 2022-03-11 13:57:02
*/
public interface EduStudentVideoService extends IService<EduStudentVideo> {


    /**
     * 通过学生ID 和 课程ID 查询学生已经观看的 视屏ID
     *
     * @param studentId
     * @param courseId
     * @return
     */
    List<Long> listStudentVideo(Long studentId, Long courseId);

    /**
     * 不存在学生观看记录就插入
     *
     * @param studentVideo
     * @return
     */
    boolean saveWhenNotExist(EduStudentVideo studentVideo);

}
