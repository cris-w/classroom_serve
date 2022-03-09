package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import top.criswjh.entity.EduPaperQuestion;
import top.criswjh.service.EduPaperQuestionService;
import top.criswjh.mapper.EduPaperQuestionMapper;
import org.springframework.stereotype.Service;

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
}




