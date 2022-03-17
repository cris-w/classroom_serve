package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduStudentQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wjh
 * @description 针对表【edu_sutdent_question(学生-试题关联表)】的数据库操作Service
 * @createDate 2022-01-22 23:41:11
 */
public interface EduStudentQuestionService extends IService<EduStudentQuestion> {

    /**
     * 通过学生ID 和 试卷ID 获取考试题目信息
     *
     * @param studentId 学生ID
     * @param paperId   试卷ID
     * @return list
     */
    List<EduStudentQuestion> listQuestionById(Long studentId, Long paperId);
}
