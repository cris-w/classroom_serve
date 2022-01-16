package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduVideo;
import top.criswjh.service.EduVideoService;

/**
 * @author wjh
 * @date 2022/1/11 11:51 PM
 */
@Api(tags = "小节管理模块")
@RestController
@RequestMapping("/edu/video")
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    /**
     * 通过Id获取小节信息
     *
     * @param videoId id
     * @return video
     */
    @GetMapping("/getVideoById/{videoId}")
    public AjaxResult<EduVideo> getVideoById(@PathVariable Long videoId) {

        EduVideo video = eduVideoService.getById(videoId);
        return AjaxResult.success(video);
    }

    /**
     * 新增小节
     *
     * @param eduVideo bo
     * @return ok
     */
    @PostMapping("/addVideo")
    public AjaxResult<Void> addVideo(@RequestBody EduVideo eduVideo) {
        eduVideo.setGmtCreate(DateUtil.date());
        eduVideoService.save(eduVideo);
        return AjaxResult.success("添加成功");
    }

    /**
     * 通过小节ID删除小节
     * 同时删除OOS中的小节视屏
     *
     * @param videoId id
     * @return ok
     */
    @GetMapping("/delVideo/{videoId}")
    public AjaxResult<Void> delVideo(@PathVariable Long videoId) {

        eduVideoService.removeVideo(videoId);
        return AjaxResult.success("删除成功");
    }

    /**
     * 修改小节信息
     *
     * @param eduVideo eduVideo
     * @return ok
     */
    @PostMapping("/updateVideo")
    public AjaxResult<Void> updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideo.setGmtUpdate(DateUtil.date());
        eduVideoService.updateById(eduVideo);
        return AjaxResult.success("修改成功");
    }

}
