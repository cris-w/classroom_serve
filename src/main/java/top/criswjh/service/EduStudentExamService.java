package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduStudentExam;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.StudentPaperBo;
import top.criswjh.entity.vo.exam.StudentExamVo;

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

    /**
     * 通过试卷id 和 班级Id 获取学生考试记录
     *
     * @param paperId 试卷ID
     * @param classId 班级ID
     * @return list
     */
    List<StudentExamVo> listExamById(Long paperId, Long classId);

    /**
     * 通过试卷ID和学生ID删除相关考试记录
     *
     * @param paperId 试卷ID
     * @param studentId 学生ID
     * @return true 删除成功 false 删除失败
     */
    Boolean removeByPaperIdAndStudentId(Long paperId, Long studentId);
}
