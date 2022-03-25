package top.criswjh.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.entity.EduExamPaper;
import top.criswjh.entity.EduExamPublish;
import top.criswjh.entity.EduPaperQuestion;
import top.criswjh.entity.bo.edu.ExamPublishBo;
import top.criswjh.entity.bo.edu.PaperBo;
import top.criswjh.entity.bo.edu.PaperQuestion;
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
@Slf4j
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

                QuestionVo questionVo = questionBankService.getQuestionById(
                        question.getQuestionId());
                choice.setId(questionVo.getId());
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

                QuestionVo questionVo = questionBankService.getQuestionById(
                        question.getQuestionId());
                completion.setId(question.getQuestionId());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPaper(PaperBo paperBo) {
        EduExamPaper examPaper = new EduExamPaper();
        BeanUtils.copyProperties(paperBo, examPaper);
        examPaper.setGmtCreate(DateUtil.date());
        examPaperMapper.insert(examPaper);

        Long id = examPaper.getId();
        log.info("id=>{}", id);

        List<PaperQuestion> questionList = paperBo.getQuestionList();
        if (questionList != null && questionList.size() > 0) {
            // 如果问题列表不为空，则转化为 EduPaperQuestion,并插入
            List<EduPaperQuestion> list = questionList.stream().map(question -> {
                EduPaperQuestion eduPaperQuestion = new EduPaperQuestion();
                BeanUtils.copyProperties(question, eduPaperQuestion);
                eduPaperQuestion.setPaperId(id);
                return eduPaperQuestion;
            }).collect(Collectors.toCollection(ArrayList<EduPaperQuestion>::new));
            paperQuestionService.saveBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePaper(PaperBo paperBo) {
        // 1. 更新试卷信息
        EduExamPaper paper = new EduExamPaper();
        BeanUtils.copyProperties(paperBo, paper);
        paper.setGmtUpdate(new Date());
        this.updateById(paper);
        // 2. 先删除试卷的题目
        paperQuestionService.remove(
                new LambdaQueryWrapper<EduPaperQuestion>().eq(EduPaperQuestion::getPaperId,
                        paperBo.getId()));
        // 3. 重新插入题目信息
        List<PaperQuestion> questionList = paperBo.getQuestionList();
        if (questionList != null && questionList.size() > 0) {
            // 如果问题列表不为空，则转化为 EduPaperQuestion,并插入
            List<EduPaperQuestion> list = questionList.stream().map(question -> {
                EduPaperQuestion eduPaperQuestion = new EduPaperQuestion();
                BeanUtils.copyProperties(question, eduPaperQuestion);
                eduPaperQuestion.setPaperId(paperBo.getId());
                return eduPaperQuestion;
            }).collect(Collectors.toCollection(ArrayList<EduPaperQuestion>::new));
            paperQuestionService.saveBatch(list);
        }
    }
}




