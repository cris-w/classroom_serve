package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 试卷-题目关联表
 * @author wjh
 */
@TableName(value ="edu_paper_question")
@Data
public class EduPaperQuestion implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 试题ID
     */
    private Long questionId;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 类型：0单选、1多选、2简答
     */
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}