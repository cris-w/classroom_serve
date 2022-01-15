package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import top.criswjh.entity.EduVideo;
import top.criswjh.service.EduVideoService;
import top.criswjh.mapper.EduVideoMapper;
import org.springframework.stereotype.Service;

/**
* @author wjh
*/
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
    implements EduVideoService{

    @Resource
    private EduVideoMapper videoMapper;

    @Override
    public void removeByCourseId(Long courseId) {
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        videoMapper.delete(wrapper.eq(EduVideo::getCourseId, courseId));
    }
}




