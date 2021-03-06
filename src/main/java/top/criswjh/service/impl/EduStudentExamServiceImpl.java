package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.entity.EduStudentExam;
import top.criswjh.entity.EduStudentQuestion;
import top.criswjh.entity.bo.edu.StudentPaperBo;
import top.criswjh.entity.vo.exam.StudentExamVo;
import top.criswjh.service.EduStudentExamService;
import top.criswjh.mapper.EduStudentExamMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduStudentQuestionService;

/**
 * @author wjh
 * @description 针对表【edu_student_exam(学生-试卷关联表)】的数据库操作Service实现
 * @createDate 2022-01-22 23:41:11
 */
@Service
public class EduStudentExamServiceImpl extends ServiceImpl<EduStudentExamMapper, EduStudentExam>
        implements EduStudentExamService {

    @Resource
    private EduStudentExamMapper eduStudentExamMapper;
    @Resource
    private EduStudentQuestionService eduStudentQuestionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean savePaper(StudentPaperBo bo) {
        // 1. 插入student-exam 表
        EduStudentExam exam = new EduStudentExam();
        BeanUtils.copyProperties(bo, exam);
        int i = eduStudentExamMapper.insert(exam);

        // 2. 插入student-question表
        List<EduStudentQuestion> list = bo.getQuestionList();
        boolean b = eduStudentQuestionService.saveBatch(list, list.size());
        return i > 0 && b;
    }

    @Override
    public List<StudentExamVo> listExamById(Long paperId, Long classId) {

        return eduStudentExamMapper.selectExamById(paperId, classId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeByPaperIdAndStudentId(Long paperId, Long studentId) {
        // 删除考试试卷信息
        boolean a = this.remove(new LambdaQueryWrapper<EduStudentExam>().eq(
                EduStudentExam::getStudentId, studentId).eq(EduStudentExam::getPaperId, paperId));
        // 删出考试题目信息
        boolean b = eduStudentQuestionService.remove(
                new LambdaQueryWrapper<EduStudentQuestion>().eq(EduStudentQuestion::getStudentId,
                        studentId).eq(EduStudentQuestion::getPaperId, paperId));
        return a && b;
    }
}




