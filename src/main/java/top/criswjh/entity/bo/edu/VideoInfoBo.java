package top.criswjh.entity.bo.edu;

import lombok.Data;

/**
 * 小节信息BO
 *
 * @author wjh
 * @date 2022/1/11 11:57 PM
 */
@Data
public class VideoInfoBo {
    private Long courseId;
    private Long chapterId;
    private String title;
}
