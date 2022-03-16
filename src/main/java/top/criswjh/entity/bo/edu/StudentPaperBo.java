package top.criswjh.entity.bo.edu;

import java.util.List;
import lombok.Data;
import top.criswjh.entity.EduStudentQuestion;

/**
 * 学生试卷
 *
 * @author wjh
 * @date 2022/3/14 9:49 PM
 */
@Data
public class StudentPaperBo {

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 问题列表
     */
    private List<EduStudentQuestion> questionList;
}
