package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionBank;
import top.criswjh.entity.EduQuestionOption;
import top.criswjh.entity.vo.exam.QuestionVo;
import top.criswjh.service.EduQuestionBankService;
import top.criswjh.mapper.EduQuestionBankMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduQuestionKnowledgeService;
import top.criswjh.service.EduQuestionOptionService;

/**
 * @author wjh
 * @description 针对表【edu_question_bank(题库表)】的数据库操作Service实现
 * @createDate 2022-01-22 23:41:11
 */
@Service
public class EduQuestionBankServiceImpl extends ServiceImpl<EduQuestionBankMapper, EduQuestionBank>
        implements EduQuestionBankService {

    @Resource
    private EduQuestionBankMapper questionBankMapper;
    @Resource
    private EduQuestionOptionService questionOptionService;
    @Resource
    private EduQuestionKnowledgeService questionKnowledgeService;

    @Override
    public List<QuestionVo> listByType(Integer type, String title) {

        List<QuestionVo> questionVos = new ArrayList<>();

        List<EduQuestionBank> questions = questionBankMapper.listByType(type, title);
        // 将每个题目的 选项 以及 关联知识点 set进Vo
        questions.forEach(question -> {
            QuestionVo questionVo = new QuestionVo();
            BeanUtils.copyProperties(question, questionVo);
            // 查询本题的选项
            List<EduQuestionOption> options = questionOptionService.list(
                    new LambdaQueryWrapper<EduQuestionOption>().eq(EduQuestionOption::getQuestionId,
                            questionVo.getId()));
            questionVo.setOptions(options);
            // 查询本题的关联知识点
            List<EduKnowledgePoint> points = questionKnowledgeService.listKnowledgeById(question.getId());
            questionVo.setKnowledgePoints(points);
            questionVos.add(questionVo);
        });
        return questionVos;
    }
}




