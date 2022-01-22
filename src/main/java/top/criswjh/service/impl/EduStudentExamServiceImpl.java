package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.EduStudentExam;
import top.criswjh.service.EduStudentExamService;
import top.criswjh.mapper.EduStudentExamMapper;
import org.springframework.stereotype.Service;

/**
* @author wjh
* @description 针对表【edu_student_exam(学生-试卷关联表)】的数据库操作Service实现
* @createDate 2022-01-22 23:41:11
*/
@Service
public class EduStudentExamServiceImpl extends ServiceImpl<EduStudentExamMapper, EduStudentExam>
    implements EduStudentExamService{

}




