package top.criswjh.entity.vo.exam;

import java.util.List;
import lombok.Data;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionOption;

/**
 * 用于试卷中 单选/多选 的题目展示
 *
 * @author wjh
 * @date 2022/2/26 8:08 PM
 */
@Data
public class MultipleChoice {

    /**
     * 题目ID
     */
    private Long id;

    /**
     * 题目描述
     */
    private String title;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目类型：0单选，1多选，2主观
     */
    private Integer type;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 选项
     */
    private List<EduQuestionOption> options;

    /**
     * 关联知识点
     */
    private List<EduKnowledgePoint> knowledgePoints;
}
