package top.criswjh.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.common.exception.MyException;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.EduCourse;
import top.criswjh.entity.EduCourseDescription;
import top.criswjh.entity.EduVideo;
import top.criswjh.entity.bo.edu.CourseInfoBo;
import top.criswjh.entity.vo.CoursePublishVo;
import top.criswjh.entity.vo.edu.CourseInfoVo;
import top.criswjh.service.EduChapterService;
import top.criswjh.service.EduCourseDescriptionService;
import top.criswjh.service.EduCourseService;
import top.criswjh.mapper.EduCourseMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduVideoService;
import top.criswjh.service.OosService;

/**
 * @author wjh
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements EduCourseService {

    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Resource
    private EduChapterService eduChapterService;
    @Resource
    private EduVideoService eduVideoService;
    @Resource
    private OosService oosService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveCourseInfo(CourseInfoBo courseInfo) {

        // edu_course 表中添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        eduCourse.setGmtCreate(DateUtil.date());
        // 设置状态为0 代表未发布
        eduCourse.setStatus(Const.STATUS_OFF);
        int insert = eduCourseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new MyException(500, "添加课程失败");
        }

        Long id = eduCourse.getId();

        // edu_course_description 表中添加描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setCourseId(id);
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setGmtCreate(DateUtil.date());
        eduCourseDescriptionService.save(eduCourseDescription);

        return id;
    }

    @Override
    public CourseInfoVo getCourseInfoById(Long courseId) {
        return eduCourseMapper.getCourseInfo(courseId);
    }

    @Override
    public void updateCourseInfo(CourseInfoVo vo) {
        // 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(vo, eduCourse);
        eduCourse.setGmtUpdate(DateUtil.date());
        eduCourseMapper.updateById(eduCourse);
        // 修改课程描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(vo.getDescription());
        description.setGmtUpdate(DateUtil.date());
        LambdaQueryWrapper<EduCourseDescription> wrapper = new LambdaQueryWrapper<>();
        eduCourseDescriptionService.update(description,
                wrapper.eq(EduCourseDescription::getCourseId, vo.getId()));
    }

    @Override
    public CoursePublishVo coursePublishInfo(Long id) {
        return eduCourseMapper.getCoursePublishInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourse(Long courseId) {
        // 删除小节视屏
        List<String> names = eduVideoService.getVideoSourceNameByCourseId(courseId);
        oosService.deleteFileBatch(names);
        // 根据课程ID 删除小节
        eduVideoService.removeByCourseId(courseId);
        // 根据课程ID 删除章节
        eduChapterService.removeByCourseId(courseId);
        // 根据课程ID 删除课程 & 描述
        eduCourseDescriptionService.removeByCourseId(courseId);
        int i = eduCourseMapper.deleteById(courseId);
        if (i == 0) {
            throw new MyException(200, "删除失败");
        }
    }

    @Override
    @Cacheable(value = "hotCourseList")
    public List<EduCourse> getHotList() {
        return eduCourseMapper.getHotList();
    }
}




