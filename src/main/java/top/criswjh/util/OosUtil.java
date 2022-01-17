package top.criswjh.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.entity.dto.VideoDto;

/**
 * 云储存配置类
 *
 * @author wjh
 * @date 2022/1/4 4:45 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "classroom.oos.qiniu")
@Slf4j
public class OosUtil {

    /**
     * 七牛云 域名
     */
    private String domain;
    /**
     * 七牛ACCESS_KEY
     */
    private String accessKey;
    /**
     * 七牛SECRET_KEY
     */
    private String secretKey;
    /**
     * 七牛空间名
     */
    private String bucketName;


    /**
     * 上传文件(流上传)
     *
     * @param file
     * @return path
     */
    public String upload(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //文件名
        String fileName = getRandomImgName(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            String path = domain + "/" + putRet.key;
            return path;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 上传视屏
     *
     * @param file
     * @return
     */
    public VideoDto uploadVideo(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //文件名
        String fileName = getRandomImgName(Objects.requireNonNull(file.getOriginalFilename()));
        VideoDto video = new VideoDto();
        video.setVideoOriginName(fileName);
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            String path = domain + "/" + putRet.key;
            video.setVideoSource(path);
            return video;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * @return
     */
    public boolean delete(String fileName) {
        Configuration configuration = new Configuration(Region.huadong());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            if (fileName != null) {
                bucketManager.delete(bucketName, fileName);
                return true;
            }
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.out.println(ex.response.toString());
        }
        return false;
    }

    public boolean deleteBatch(List<String> fileList) {
        Configuration configuration = new Configuration(Region.huadong());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            String[] files = fileList.toArray(new String[0]);
            batchOperations.addDeleteOp(bucketName, files);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < fileList.size(); i++) {
                BatchStatus status = batchStatusList[i];
                if (status.code == 200) {
                    log.info("{}---->删除成功", fileList.get(i));
                } else {
                    log.error("删除失败：【{}】", status.data.error);
                    return false;
                }
            }
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.out.println(ex.response.toString());
        }
        return false;
    }

    /**
     * 生成随机图片ID，防止重名
     *
     * @param fileName 原文件名
     * @return 随机文件名：日期 + uuid + 后缀
     */
    public String getRandomImgName(String fileName) {

        int index = fileName.lastIndexOf(".");

        if (StrUtil.isEmpty(fileName) || index == -1) {
            throw new IllegalArgumentException("文件为空");
        }
        // 获取文件后缀 如 .png
        String suffix = fileName.substring(index);
        // 生成UUID
        String uuid = IdUtil.fastSimpleUUID();
        // 生成上传至云服务器的路径
        String path = DateUtil.today() + "-" + uuid + suffix;
        return path;
    }
}
