package top.criswjh.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.criswjh.common.lang.AjaxResult;
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
    OosService oosService;

    @PostMapping("/upload")
    public AjaxResult<String> uploadFile(@RequestPart("file")MultipartFile file) {

        String path = oosService.uploadAvatar(file);

        if (StrUtil.isEmpty(path)) {
            return AjaxResult.error("上传失败", path);
        }

        return AjaxResult.success("上传成功", path);
    }
}
