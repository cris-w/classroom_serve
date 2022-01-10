package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduChapter;
import top.criswjh.entity.vo.edu.ChapterVo;
import top.criswjh.service.EduChapterService;

/**
 * @author wjh
 * @date 2022/1/9 9:10 PM
 */
@Api(tags = "章节管理模块")
@RestController
@RequestMapping("/edu/chapter")
public class EduChapterController {

    @Resource
    private EduChapterService eduChapterService;

    /**
     * 根据课程Id查询章节和对应的小节信息
     *
     * @return list
     */
    @GetMapping("/getChapter/{courseId}")
    public AjaxResult<List<ChapterVo>> getChapter(@PathVariable Long courseId) {
        List<ChapterVo> list = eduChapterService.getChapterAndVideoByCourseId(courseId);
        return AjaxResult.success(list);
    }


    /**
     * 添加章节
     *
     * @param eduChapter chapter
     * @return ok
     */
    @PostMapping("/addChapter")
    public AjaxResult<Void> addChapter(@RequestBody EduChapter eduChapter) {
        eduChapter.setGmtCreate(DateUtil.date());
        eduChapterService.save(eduChapter);
        return AjaxResult.success("添加成功");
    }

    /**
     * 通过章节ID查询章节信息
     *
     * @param chapterId id
     * @return chapter
     */
    @GetMapping("/getChapterInfo/{chapterId}")
    public AjaxResult<EduChapter> getChapterInfo(@PathVariable Long chapterId) {
        EduChapter chapter = eduChapterService.getById(chapterId);
        return AjaxResult.success(chapter);
    }

    /**
     * 修改章节信息
     *
     * @param eduChapter eduChapter
     * @return ok
     */
    @PostMapping("/updateChapter")
    public AjaxResult<Void> updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapter.setGmtUpdate(DateUtil.date());
        eduChapterService.updateById(eduChapter);
        return AjaxResult.success("修改成功");
    }

    /**
     * 删除章节信息,如果该章节包含小节则不让删除
     *
     * @param chapterId courseId
     * @return ok
     */
    @DeleteMapping("/deleteChapter/{chapterId}")
    public AjaxResult<Void> deleteChapterInfo(@PathVariable Long chapterId) {
        boolean flag = eduChapterService.deleteById(chapterId);
        if (flag) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
}
