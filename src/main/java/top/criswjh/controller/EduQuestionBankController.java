package top.criswjh.controller;

import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.bo.edu.QuestionBo;
import top.criswjh.entity.vo.exam.QuestionVo;
import top.criswjh.service.EduQuestionBankService;

/**
 * @author wjh
 * @date 2022/2/8 5:14 PM
 */
@Api(tags = "题库模块")
@RestController
@RequestMapping("/edu/questionBank")
public class EduQuestionBankController extends BaseController {

    @Resource
    private EduQuestionBankService questionBankService;

    /**
     * 获取所有题目(模糊查询)
     * 包括 知识点、选项
     *
     * @param type 0 单选 1 多选 2 简答
     * @param title 标题
     *
     * @return list
     */
    @GetMapping("/list")
    public AjaxResult<List<QuestionVo>> list(String title, Integer type) {

        List<QuestionVo> eduQuestionBanks = questionBankService.listByType(type, title);

        return AjaxResult.success(eduQuestionBanks);
    }

    /**
     * 通过id获取问题
     * 包括 题目 选项 关联知识点
     *
     * @param id 题目id
     * @return vo
     */
    @GetMapping("/getQuestionById/{id}")
    public AjaxResult<QuestionVo> getQuestionById(@PathVariable Long id) {

        QuestionVo vo = questionBankService.getQuestionById(id);

        return AjaxResult.success(vo);
    }

    /**
     * 创建题库问题
     *
     * @param bo bo
     * @return ok
     */
    @PostMapping("/addQuestion")
    public AjaxResult<Void> addQuestion(@RequestBody QuestionBo bo) {

        questionBankService.addQuestion(bo);

        return AjaxResult.success("创建成功");
    }

    /**
     * 通过id删除题目数据
     * 包括 知识点表 选项表 题库表
     *
     * @param id 题目id
     * @return ok
     */
    @GetMapping("/delete/{id}")
    public AjaxResult<Void> delete(@PathVariable Long id) {
        questionBankService.deleteQuestionById(id);

        return AjaxResult.success("删除成功");
    }

    /**
     * 修改题目
     *
     * @param bo bo
     * @return ok
     */
    @PostMapping("/update")
    public AjaxResult<Void> update(@RequestBody QuestionBo bo) {
        questionBankService.updateQuestion(bo);

        return AjaxResult.success("修改成功");
    }
}
