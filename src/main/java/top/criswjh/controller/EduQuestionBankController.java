package top.criswjh.controller;

import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
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
     * @return list
     */
    @GetMapping("/list")
    public AjaxResult<List<QuestionVo>> list(String title, Integer type) {

        List<QuestionVo> eduQuestionBanks = questionBankService.listByType(type, title);

        return AjaxResult.success(eduQuestionBanks);
    }
}
