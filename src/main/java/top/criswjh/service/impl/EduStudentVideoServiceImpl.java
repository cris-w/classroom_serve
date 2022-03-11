package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.entity.EduStudentVideo;
import top.criswjh.service.EduStudentVideoService;
import top.criswjh.mapper.EduStudentVideoMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【edu_student_video(学生-视频关联表)】的数据库操作Service实现
 * @createDate 2022-03-11 13:57:02
 */
@Service
public class EduStudentVideoServiceImpl extends ServiceImpl<EduStudentVideoMapper, EduStudentVideo>
        implements EduStudentVideoService {

    @Resource
    private EduStudentVideoMapper studentVideoMapper;

    @Override
    public List<Long> listStudentVideo(Long studentId, Long courseId) {

        return studentVideoMapper.selectStudentVideoList(studentId, courseId);
    }

    @Override
    public boolean saveWhenNotExist(EduStudentVideo studentVideo) {
        Integer exist = studentVideoMapper.selectExist(studentVideo.getStudentId(),
                studentVideo.getCourseId(),
                studentVideo.getVideoId());
        if (exist > 0) {
            return false;
        }
        return studentVideoMapper.insert(studentVideo) > 0;
    }
}




