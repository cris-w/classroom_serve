package top.criswjh.entity.bo.edu;

import java.util.List;
import lombok.Data;

/**
 * @author wjh
 * @date 2022/2/27 9:14 PM
 */
@Data
public class PaperBo {
    /**
     * 试卷唯一标识
     */
    private Long id;

    /**
     * 老师姓名
     */
    private String teacherName;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 试卷描述
     */
    private String description;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 题目列表
     */
    private List<PaperQuestion> questionList;
}
