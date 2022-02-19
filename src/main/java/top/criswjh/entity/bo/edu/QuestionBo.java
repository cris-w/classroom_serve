package top.criswjh.entity.bo.edu;

import java.util.List;
import lombok.Data;

/**
 * @author wjh
 * @date 2022/2/11 9:31 PM
 */
@Data
public class QuestionBo {

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
    private List<String> options;

    /**
     * 关联知识点id
     */
    private List<Long> knowledgePoints;
}
