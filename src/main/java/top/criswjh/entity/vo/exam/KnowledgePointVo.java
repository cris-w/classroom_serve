package top.criswjh.entity.vo.exam;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author wjh
 * @date 2022/1/24 9:34 PM
 */
@Data
public class KnowledgePointVo {

    /**
     * id
     */
    private Long id;
    /**
     * 标签
     */
    private String title;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 子集
     */
    private List<KnowledgePointVo> children =new ArrayList<>();
}
