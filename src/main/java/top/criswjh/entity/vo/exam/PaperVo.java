package top.criswjh.entity.vo.exam;

import java.util.List;
import lombok.Data;

/**
 * 试卷
 *
 * @author wjh
 * @date 2022/2/26 8:20 PM
 */
@Data
public class PaperVo {

    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 选择题
     */
    private List<MultipleChoice> choices;
    /**
     * 简答题
     */
    private List<Completion> completions;
}
