package top.criswjh.service;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.entity.dto.VideoDto;

/**
 * @author wjh
 * @date 2022/1/4 5:42 PM
 */
public interface OosService {


    /**
     * 上传头像到OOS
     *
     * @param file
     * @return
     */
    String uploadAvatar(MultipartFile file);

    /**
     * 上传视屏到OOS
     *
     * @param file
     * @return
     */
    VideoDto uploadVideo(MultipartFile file);

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    Boolean deleteFile(String fileName);

    /**
     * 批量删除文件
     *
     * @param fileList
     * @return
     */
    Boolean deleteFileBatch(List<String> fileList);
}
