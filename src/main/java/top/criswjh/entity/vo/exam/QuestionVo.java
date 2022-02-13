package top.criswjh.entity.vo.exam;

import java.util.List;
import lombok.Data;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionOption;

/**
 * @author wjh
 * @date 2022/2/11 9:31 PM
 */
@Data
public class QuestionVo {

    /**
     * 题库唯一标识
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
     * 题目等级：0简单，1中等，2困难
     */
    private Integer level;

    /**
     * 选项
     */
    private List<EduQuestionOption> options;

    /**
     * 关联知识点
     */
    private List<EduKnowledgePoint> knowledgePoints;
}
