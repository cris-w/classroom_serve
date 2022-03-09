package top.criswjh.entity.bo.edu;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author wjh
 * @date 2022/3/9 3:32 PM
 */
@Data
public class ExamPublishBo {
    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 试卷标题
     */
    private String paperTitle;

    /**
     * 班级id
     */
    private List<Long> classIds;

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
