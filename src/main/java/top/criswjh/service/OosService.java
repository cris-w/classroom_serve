package top.criswjh.service;


import org.springframework.web.multipart.MultipartFile;

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
     * 删除文件
     *
     * @param fileName
     * @return
     */
    Boolean deleteFile(String fileName);
}
