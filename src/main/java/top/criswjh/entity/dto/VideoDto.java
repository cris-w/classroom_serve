package top.criswjh.entity.dto;

import lombok.Data;

/**
 * 上传视屏保存 视屏地址 以及 视屏文件名
 *
 * @author wjh
 * @date 2022/1/16 6:59 PM
 */
@Data
public class VideoDto {

    private String videoSource;
    private String videoOriginName;

}
