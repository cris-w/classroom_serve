package top.criswjh.entity.vo.exam;

import lombok.Data;

/**
 * @author wjh
 * @date 2022/3/21 17:26
 */
@Data
public class QuestionBriefVo {
    /**
     * 题库唯一标识
     */
    private Long id;

    /**
     * 题目描述
     */
    private String title;

    /**
     * 题目类型：0单选，1多选，2主观
     */
    private Integer type;

    /**
     * 分数
     */
    private Integer score;
}
