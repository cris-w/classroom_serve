package top.criswjh.entity.bo.edu;

import lombok.Data;

/**
 * 学生考试题目答案
 *
 * @author wjh
 * @date 2022/3/14 9:54 PM
 */
@Data
public class StudentQuestion {

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 答案
     */
    private String answer;
}
