package top.criswjh.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.entity.EduVideo;
import top.criswjh.service.EduVideoService;
import top.criswjh.mapper.EduVideoMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.OosService;

/**
 * @author wjh
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
        implements EduVideoService {

    @Resource
    private EduVideoMapper videoMapper;
    @Resource
    private OosService oosService;

    @Override
    public void removeByCourseId(Long courseId) {
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        videoMapper.delete(wrapper.eq(EduVideo::getCourseId, courseId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeVideo(Long videoId) {
        // 通过ID 查询出 Video对象
        EduVideo video = videoMapper.selectById(videoId);

        if (!StrUtil.isEmpty(video.getVideoOriginName())) {
            // 调用OosService 通过 Video中的 VideoOriginName删除视屏
            oosService.deleteFile(video.getVideoOriginName());
        }
        // 删除小节
        videoMapper.deleteById(videoId);
    }
}




