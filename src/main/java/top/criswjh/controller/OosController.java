package top.criswjh.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.dto.VideoDto;
import top.criswjh.service.OosService;

/**
 * @author wjh
 * @date 2022/1/4 5:43 PM
 */
@Api(tags = "对象存储模块模块")
@RestController
@RequestMapping("/oos/fileOos")
public class OosController {

    @Resource
    private OosService oosService;

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public AjaxResult<String> uploadFile(@RequestPart("file") MultipartFile file) {

        String path = oosService.uploadAvatar(file);

        if (StrUtil.isEmpty(path)) {
            return AjaxResult.error("上传失败", path);
        }

        return AjaxResult.success("上传成功", path);
    }

    /**
     * 上传视屏
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadVideo")
    public AjaxResult<VideoDto> uploadVideo(@RequestPart("file") MultipartFile file) {

        VideoDto video = oosService.uploadVideo(file);

        if (StrUtil.isEmpty(video.getVideoSource()) && StrUtil.isEmpty(
                video.getVideoOriginName())) {
            return AjaxResult.error("上传失败", video);
        }

        return AjaxResult.success("上传成功", video);
    }

    /**
     * 通过文件名删除文件
     *
     * @param fileName
     * @return
     */
    @GetMapping("/delete/{fileName}")
    public AjaxResult<String> deleteFile(@PathVariable String fileName) {
        if (oosService.deleteFile(fileName)) {
            return AjaxResult.success("删除成功", fileName);
        }
        return AjaxResult.success("删除失败", fileName);
    }

    /**
     * 批量删除文件
     *
     * @param fileList
     * @return
     */
    @PostMapping("/deleteBatch")
    public AjaxResult<List<String>> deleteBatch(@RequestBody List<String> fileList) {
        if (oosService.deleteFileBatch(fileList)) {
            return AjaxResult.success("删除成功", fileList);
        }
        return AjaxResult.success("删除失败", fileList);
    }
}
