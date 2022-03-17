package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import top.criswjh.entity.EduExamPublish;
import top.criswjh.entity.EduStudentClass;
import top.criswjh.entity.bo.edu.ExamPublishBo;
import top.criswjh.entity.vo.exam.ExamPublishVo;
import top.criswjh.service.EduExamPublishService;
import top.criswjh.mapper.EduExamPublishMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduStudentClassService;
import top.criswjh.service.EduStudentExamService;

/**
 * @author wjh
 * @description 针对表【edu_exam_publish(发布考试表)】的数据库操作Service实现
 * @createDate 2022-02-26 15:16:38
 */
@Service
public class EduExamPublishServiceImpl extends ServiceImpl<EduExamPublishMapper, EduExamPublish>
        implements EduExamPublishService {

    @Resource
    private EduExamPublishMapper examPublishMapper;
    @Resource
    private EduStudentClassService eduStudentClassService;
    @Resource
    private EduStudentExamService eduStudentExamService;

    @Override
    public Boolean saveExam(ExamPublishBo bo) {
        List<Long> classIds = bo.getClassIds();
        if (!CollectionUtils.isEmpty(classIds)) {
            List<EduExamPublish> list = new ArrayList<>(classIds.size());
            classIds.forEach(id -> {
                EduExamPublish exam = new EduExamPublish();
                BeanUtils.copyProperties(bo, exam);
                exam.setClassId(id);
                list.add(exam);
            });
            return this.saveBatch(list);
        }
        return false;
    }

    @Override
    public List<ExamPublishVo> listPublishVo(String title, Long classId) {

        if (title != null) {
            return examPublishMapper.selectPublishVo(title.trim(), classId);
        }
        return examPublishMapper.selectPublishVo(null, classId);
    }

    @Override
    public Boolean removePaper(Long id) {
        // 查询试卷ID 和 班级ID
        EduExamPublish exam = examPublishMapper.selectById(id);
        Long paperId = exam.getPaperId();
        Long classId = exam.getClassId();

        // 通过班级ID 查询所有的学生ID
        List<EduStudentClass> list = eduStudentClassService.list(
                new LambdaQueryWrapper<EduStudentClass>().eq(EduStudentClass::getClassId, classId));
        List<Long> studentIds = list.stream().map(EduStudentClass::getStudentId)
                .collect(Collectors.toList());

        // 通过学生ID 和 试卷ID 删除考试相关信息
        studentIds.forEach(s -> eduStudentExamService.removeByPaperIdAndStudentId(paperId, s));

        // 删除发布试卷信息
        return removeById(id);
    }
}




