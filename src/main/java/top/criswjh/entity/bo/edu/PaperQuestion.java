package top.criswjh.entity.bo.edu;

import lombok.Data;

/**
 * @author wjh
 * @date 2022/2/27 9:22 PM
 */
@Data
public class PaperQuestion {

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 类型：0 单选、1 多选、2 简答
     */
    private Integer type;
}
