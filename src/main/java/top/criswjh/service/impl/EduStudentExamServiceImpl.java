package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.entity.EduStudentExam;
import top.criswjh.entity.EduStudentQuestion;
import top.criswjh.entity.bo.edu.StudentPaperBo;
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
}




