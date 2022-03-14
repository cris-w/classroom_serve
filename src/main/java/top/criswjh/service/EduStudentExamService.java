package top.criswjh.service;

import top.criswjh.entity.EduStudentExam;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.StudentPaperBo;

/**
* @author wjh
* @description 针对表【edu_student_exam(学生-试卷关联表)】的数据库操作Service
* @createDate 2022-01-22 23:41:11
*/
public interface EduStudentExamService extends IService<EduStudentExam> {

    /**
     * 新增学生考试记录
     *
     * @param bo bo
     * @return true 操作成功 false 操作失败
     */
    Boolean savePaper(StudentPaperBo bo);
}
