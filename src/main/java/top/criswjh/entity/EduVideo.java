package top.criswjh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author wjh
 */
@TableName(value ="edu_video")
@Data
public class EduVideo implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 标题
     */
    private String title;

    /**
     * 视屏ID
     */
    private Long videoSourceId;

    /**
     * 视屏源文件名
     */
    private String videoOriginName;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 播放次数
     */
    private Long playCount;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}