package top.criswjh.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import top.criswjh.entity.EduExamPaper;
import top.criswjh.entity.bo.edu.ExamPublishBo;
import top.criswjh.entity.bo.edu.PaperBo;
import top.criswjh.entity.vo.exam.ExamPublishVo;
import top.criswjh.entity.vo.exam.PaperVo;
import top.criswjh.service.EduExamPaperService;
import top.criswjh.service.EduExamPublishService;
import top.criswjh.service.EduPaperQuestionService;

/**
 * @author wjh
 * @date 2022/2/25 2:21 PM
 */
@Api(tags = "试卷管理")
@RestController
@RequestMapping("/edu/examPaper")
public class EduExamPaperController {

    @Resource
    private EduExamPaperService examPaperService;
    @Resource
    private EduPaperQuestionService paperQuestionService;
    @Resource
    private EduExamPublishService eduExamPublishService;

    /**
     * 获取所有试卷（模糊查询）
     *
     * @param title 标题
     * @return 试卷列表
     */
    @GetMapping("/list")
    public AjaxResult<List<EduExamPaper>> list(String title) {
        List<EduExamPaper> list = examPaperService.list(new LambdaQueryWrapper<EduExamPaper>()
                .like(StrUtil.isNotBlank(title), EduExamPaper::getTitle, title));
        return AjaxResult.success(list);
    }

    /**
     * 通过id获取试卷的题目信息
     *
     * @param id id
     * @return paperVo
     */
    @GetMapping("/getPaperById/{id}")
    public AjaxResult<PaperVo> getPaperById(@PathVariable Long id) {
        PaperVo paper = examPaperService.getQuestionListById(id);
        return AjaxResult.success(paper);
    }

    /**
     * 创建试卷
     *
     * @param paperBo paper
     * @return ok
     */
    @PostMapping("/save")
    public AjaxResult<Void> save(@RequestBody PaperBo paperBo) {
        examPaperService.createPaper(paperBo);
        return AjaxResult.success("创建成功");
    }

    /**
     * 通过ids批量删除
     *
     * @param ids ids
     * @return ids
     */
    @DeleteMapping("/delete")
    public AjaxResult<List<Long>> delete(@RequestBody List<Long> ids) {
        // 删除试卷信息
        examPaperService.removeByIds(ids);
        // 删除试卷题目信息
        paperQuestionService.removeByPaperIds(ids);

        return AjaxResult.success("删除成功", ids);
    }

    /**
     * 发布试卷
     *
     * @param bo bo
     * @return ok
     */
    @PostMapping("/publishExam")
    public AjaxResult<Void> publishExam(@RequestBody ExamPublishBo bo) {

        if (eduExamPublishService.saveExam(bo)) {
            return AjaxResult.success("发布成功");
        }
        return AjaxResult.error("发布失败");
    }

    /**
     * 获取发布试卷信息
     *
     * @param title
     * @param classId
     * @return
     */
    @GetMapping("/listPublishExam")
    public AjaxResult<List<ExamPublishVo>> listPublishExam(String title, Long classId) {
        List<ExamPublishVo> list = eduExamPublishService.listPublishVo(title, classId);

        return AjaxResult.success(list);
    }

}