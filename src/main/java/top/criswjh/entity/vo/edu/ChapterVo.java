package top.criswjh.entity.vo.edu;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 章节Vo
 *
 * @author wjh
 * @date 2022/1/9 9:04 PM
 */
@Data
public class ChapterVo {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 小节
     */
    private List<VideoVo> children = new ArrayList<>();
}
