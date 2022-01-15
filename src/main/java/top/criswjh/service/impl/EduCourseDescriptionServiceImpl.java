package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import top.criswjh.entity.EduCourseDescription;
import top.criswjh.service.EduCourseDescriptionService;
import top.criswjh.mapper.EduCourseDescriptionMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 */
@Service
public class EduCourseDescriptionServiceImpl extends
        ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription>
        implements EduCourseDescriptionService {

    @Resource
    private EduCourseDescriptionMapper courseDescriptionMapper;

    @Override
    public void removeByCourseId(Long courseId) {
        LambdaQueryWrapper<EduCourseDescription> wrapper = new LambdaQueryWrapper<>();
        courseDescriptionMapper.delete(wrapper.eq(EduCourseDescription::getCourseId, courseId));
    }
}




