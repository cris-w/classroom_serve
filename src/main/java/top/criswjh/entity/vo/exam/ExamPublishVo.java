package top.criswjh.entity.vo.exam;

import java.util.Date;
import lombok.Data;

/**
 * @author wjh
 * @date 2022/3/10 12:18 AM
 */
@Data
public class ExamPublishVo {

    /**
     * id
     */
    private Long id;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 试卷标题
     */
    private String paperTitle;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 限制时间：分钟
     */
    private Integer timeLimit;

    /**
     * 开始时间
     */
    private Date timeStart;

    /**
     * 结束时间
     */
    private Date timeEnd;
}
