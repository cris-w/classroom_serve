package top.criswjh.entity.vo.exam;

import java.util.Date;
import lombok.Data;

/**
 * 学生考试记录
 *
 * @author wjh
 * @date 2022/3/16 20:10
 */
@Data
public class StudentExamVo {

    /**
     * ID
     */
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 试卷ID
     */
    private Long paperId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 批阅时间
     */
    private Date readTime;

    /**
     * 批阅人名
     */
    private String readName;

    /**
     * 状态：0未批阅、1已批阅
     */
    private Integer state;

    /**
     * 考试成绩
     */
    private Integer score;
}
