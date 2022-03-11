package top.criswjh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.common.exception.MyException;
import top.criswjh.entity.EduChapter;
import top.criswjh.entity.EduVideo;
import top.criswjh.entity.vo.course.ChapterVo;
import top.criswjh.entity.vo.course.VideoVo;
import top.criswjh.service.EduChapterService;
import top.criswjh.mapper.EduChapterMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduVideoService;

/**
* @author wjh
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService{

    @Resource
    private EduChapterMapper eduChapterMapper;
    @Resource
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterAndVideoByCourseId(Long courseId) {
        // 根据ID 查询所有章节
        LambdaQueryWrapper<EduChapter> chapterWrapper = new LambdaQueryWrapper<>();
        List<EduChapter> eduChapterList = eduChapterMapper.selectList(
                chapterWrapper.eq(EduChapter::getCourseId, courseId).orderByAsc(EduChapter::getSort));
        // 根据课程ID 查询所有小节
        LambdaQueryWrapper<EduVideo> videoWrapper = new LambdaQueryWrapper<>();
        List<EduVideo> eduVideoList = eduVideoService.list(
                videoWrapper.eq(EduVideo::getCourseId, courseId).orderByAsc(EduVideo::getSort));

        // 将对应的小节插入到对应章节中
        List<ChapterVo> result = new ArrayList<>();
        eduChapterList.forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtil.copyProperties(chapter, chapterVo);

            List<VideoVo> list = new ArrayList<>();
            // 遍历videoList ， 放入对应章节中。
            eduVideoList.forEach(video -> {
                if (video.getChapterId().equals(chapterVo.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtil.copyProperties(video, videoVo);
                    list.add(videoVo);
                }
            });
            chapterVo.setChildren(list);
            result.add(chapterVo);
        });

        return result;
    }

    @Override
    public boolean deleteById(Long chapterId) {
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        long count = eduVideoService.count(wrapper.eq(EduVideo::getChapterId, chapterId));
        if (count > 0) {
            throw new MyException(400, "请先删除该章节的小节");
        }

        return eduChapterMapper.deleteById(chapterId) > 0;
    }

    @Override
    public void removeByCourseId(Long courseId) {
        LambdaQueryWrapper<EduChapter> wrapper = new LambdaQueryWrapper<>();
        eduChapterMapper.delete(wrapper.eq(EduChapter::getCourseId, courseId));
    }
}




