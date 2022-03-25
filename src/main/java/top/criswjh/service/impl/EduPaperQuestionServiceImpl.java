package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import top.criswjh.entity.EduPaperQuestion;
import top.criswjh.entity.EduQuestionBank;
import top.criswjh.entity.vo.exam.QuestionBriefVo;
import top.criswjh.service.EduPaperQuestionService;
import top.criswjh.mapper.EduPaperQuestionMapper;
import org.springframework.stereotype.Service;
import top.criswjh.service.EduQuestionBankService;

/**
 * @author wjh
 * @description 针对表【edu_paper_question(试卷-题目关联表)】的数据库操作Service实现
 * @createDate 2022-01-22 23:41:11
 */
@Service
public class EduPaperQuestionServiceImpl extends
        ServiceImpl<EduPaperQuestionMapper, EduPaperQuestion>
        implements EduPaperQuestionService {

    @Resource
    private EduPaperQuestionMapper paperQuestionMapper;
    @Resource
    private EduQuestionBankService eduQuestionBankService;

    @Override
    public Integer removeByPaperIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        AtomicInteger nums = new AtomicInteger();
        ids.forEach(id -> {
            nums.addAndGet(paperQuestionMapper.delete(new LambdaQueryWrapper<EduPaperQuestion>()
                    .eq(EduPaperQuestion::getPaperId, id)));
        });
        return nums.get();
    }

    @Override
    public List<QuestionBriefVo> listQuestionBriefVoById(Long paperId) {
        List<EduPaperQuestion> list = this.list(
                new LambdaQueryWrapper<EduPaperQuestion>().eq(EduPaperQuestion::getPaperId,
                        paperId));
        List<QuestionBriefVo> vos = list.stream().map(l -> {
                    QuestionBriefVo vo = new QuestionBriefVo();
                    vo.setId(l.getQuestionId());
                    vo.setScore(l.getScore());
                    return vo;
                })
                .collect(Collectors.toList());
        vos.forEach(vo -> {
            EduQuestionBank question = eduQuestionBankService.getById(vo.getId());
            vo.setTitle(question.getTitle());
            vo.setType(question.getType());
        });
        return vos;
    }
}




