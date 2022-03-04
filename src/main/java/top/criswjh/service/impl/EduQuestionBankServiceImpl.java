package top.criswjh.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionBank;
import top.criswjh.entity.EduQuestionKnowledge;
import top.criswjh.entity.EduQuestionOption;
import top.criswjh.entity.bo.edu.QuestionBo;
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
            List<EduKnowledgePoint> points = questionKnowledgeService.listKnowledgeById(
                    question.getId());
            questionVo.setKnowledgePoints(points);
            questionVos.add(questionVo);
        });
        return questionVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestionById(Long id) {
        // 删除知识点关联表相关数据
        questionKnowledgeService.deleteByQuestionId(id);
        // 删除选项关联表相关数据
        questionOptionService.deleteByQuestionId(id);
        // 删除题库表数据
        questionBankMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addQuestion(QuestionBo bo) {
        // 插入题库表
        EduQuestionBank eduQuestionBank = new EduQuestionBank();
        BeanUtil.copyProperties(bo, eduQuestionBank);
        eduQuestionBank.setGmtCreate(DateUtil.date());
        questionBankMapper.insert(eduQuestionBank);
        // 插入题目选项关联表
        Long questionBankId = eduQuestionBank.getId();
        this.insertOptions(bo, questionBankId);
        // 插入题目知识点关联表
        insertKnowledgePoints(bo, questionBankId);
    }

    @Override
    public QuestionVo getQuestionById(Long id) {
        QuestionVo questionVo = new QuestionVo();
        EduQuestionBank question = questionBankMapper.selectById(id);
        if (question == null) {
            return null;
        }
        BeanUtils.copyProperties(question, questionVo);
        // 如果题目类型为 2 主观题，则不需要查询选项
        if (questionVo.getType() != 2) {
            List<EduQuestionOption> options = questionOptionService.getByQuestionId(id);
            questionVo.setOptions(options);
        }
        List<EduKnowledgePoint> knowledgeList = questionKnowledgeService.getByQuestionId(id);
        questionVo.setKnowledgePoints(knowledgeList);
        return questionVo;
    }

    @Override
    public void updateQuestion(QuestionBo bo) {
        // 修改题库表数据
        EduQuestionBank questionBank = new EduQuestionBank();
        questionBank.setGmtUpdate(DateUtil.date());
        BeanUtils.copyProperties(bo, questionBank);
        questionBankMapper.updateById(questionBank);
        // 修改问题选项表
        // 1. 删除原来的选项
        questionOptionService.deleteByQuestionId(bo.getId());
        // 2. 插入新选项
        insertOptions(bo, bo.getId());
        // 修改问题知识点关联表
        // 1. 删除原来的知识点
        questionKnowledgeService.deleteByQuestionId(bo.getId());
        // 2. 插入新的知识点
        this.insertKnowledgePoints(bo, bo.getId());
    }

    @Override
    public List<EduQuestionBank> listBrief(Integer type, String title) {
        return questionBankMapper.listByType(type, title);
    }

    /**
     * 插入选项
     *
     * @param bo QuestionBo
     * @param id questionId
     */
    public void insertOptions(QuestionBo bo, Long id) {
        List<String> options = bo.getOptions();
        // 如果选项为空 则直接跳出
        if (options == null) {
            return;
        }
        List<EduQuestionOption> eduQuestionOptions = new ArrayList<>();
        options.forEach(option -> {
            EduQuestionOption eduQuestionOption = new EduQuestionOption();
            eduQuestionOption.setQuestionId(id);
            eduQuestionOption.setOption(option);
            eduQuestionOptions.add(eduQuestionOption);
        });
        questionOptionService.saveBatch(eduQuestionOptions);
    }

    /**
     * 插入知识点
     *
     * @param bo
     * @param id
     */
    public void insertKnowledgePoints(QuestionBo bo, Long id) {
        List<Long> knowledgePoints = bo.getKnowledgePoints();
        // 如果知识点为空，则直接返回
        if( knowledgePoints == null) {
            return;
        }
        List<EduQuestionKnowledge> knowledgeList = new ArrayList<>();
        knowledgePoints.forEach(pointsId -> {
            EduQuestionKnowledge eduQuestionKnowledge = new EduQuestionKnowledge();
            eduQuestionKnowledge.setQuestionId(id);
            eduQuestionKnowledge.setKnowledgeId(pointsId);
            knowledgeList.add(eduQuestionKnowledge);
        });
        questionKnowledgeService.saveBatch(knowledgeList);
    }
}




