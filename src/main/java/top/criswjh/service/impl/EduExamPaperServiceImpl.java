package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.entity.EduExamPaper;
import top.criswjh.entity.EduPaperQuestion;
import top.criswjh.entity.vo.exam.Completion;
import top.criswjh.entity.vo.exam.MultipleChoice;
import top.criswjh.entity.vo.exam.PaperVo;
import top.criswjh.entity.vo.exam.QuestionVo;
import top.criswjh.service.EduExamPaperService;
import top.criswjh.mapper.EduExamPaperMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduPaperQuestionService;
import top.criswjh.service.EduQuestionBankService;

/**
 * @author wjh
 */
@Service
public class EduExamPaperServiceImpl extends ServiceImpl<EduExamPaperMapper, EduExamPaper>
        implements EduExamPaperService {

    @Resource
    private EduExamPaperMapper examPaperMapper;
    @Resource
    private EduPaperQuestionService paperQuestionService;
    @Resource
    private EduQuestionBankService questionBankService;


    @Override
    public PaperVo getQuestionListById(Long id) {

        PaperVo paperVo = new PaperVo();
        List<MultipleChoice> choices = new ArrayList<>();
        List<Completion> completions = new ArrayList<>();

        // 通过试卷id 获取试卷标题
        EduExamPaper eduExamPaper = examPaperMapper.selectById(id);
        paperVo.setTitle(eduExamPaper.getTitle());
        paperVo.setDescription(eduExamPaper.getDescription());

        // 通过试卷id 获取试卷问题
        List<EduPaperQuestion> paperQuestions = paperQuestionService.list(
                new LambdaQueryWrapper<EduPaperQuestion>().eq(EduPaperQuestion::getPaperId, id));
        paperQuestions.forEach(question -> {
            if (question.getType() != 2) {
                // 如果为选择题
                MultipleChoice choice = new MultipleChoice();
                choice.setScore(question.getScore());
                choice.setType(question.getType());

                QuestionVo questionVo = questionBankService.getQuestionById(question.getQuestionId());
                choice.setTitle(questionVo.getTitle());
                choice.setAnswer(questionVo.getAnswer());
                choice.setOptions(questionVo.getOptions());
                choice.setKnowledgePoints(questionVo.getKnowledgePoints());
                choices.add(choice);
            } else {
                // 如果为主观题
                Completion completion = new Completion();
                completion.setScore(question.getScore());
                completion.setType(question.getType());

                QuestionVo questionVo = questionBankService.getQuestionById(question.getQuestionId());
                completion.setTitle(questionVo.getTitle());
                completion.setAnswer(questionVo.getAnswer());
                completion.setKnowledgePoints(questionVo.getKnowledgePoints());
                completions.add(completion);
            }
        });
        paperVo.setChoices(choices);
        paperVo.setCompletions(completions);
        return paperVo;
    }
}




