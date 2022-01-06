package top.criswjh.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.common.exception.MyException;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.EduCourse;
import top.criswjh.entity.EduCourseDescription;
import top.criswjh.entity.bo.CourseInfoBo;
import top.criswjh.mapper.EduCourseDescriptionMapper;
import top.criswjh.service.EduCourseDescriptionService;
import top.criswjh.service.EduCourseService;
import top.criswjh.mapper.EduCourseMapper;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveCourseInfo(CourseInfoBo courseInfo) {

        // edu_course 表中添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        eduCourse.setGmtCreate(DateUtil.date());
        eduCourse.setStatus(Const.STATUS_ON);
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
}




