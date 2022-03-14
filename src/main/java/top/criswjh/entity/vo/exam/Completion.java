package top.criswjh.entity.vo.exam;

import java.util.List;
import lombok.Data;
import top.criswjh.entity.EduKnowledgePoint;

/**
 * 主观题
 *
 * @author wjh
 * @date 2022/2/26 8:18 PM
 */
@Data
public class Completion {

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
     * 关联知识点
     */
    private List<EduKnowledgePoint> knowledgePoints;
}
