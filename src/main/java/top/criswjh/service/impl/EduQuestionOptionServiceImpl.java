package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.entity.EduQuestionOption;
import top.criswjh.service.EduQuestionOptionService;
import top.criswjh.mapper.EduQuestionOptionMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 */
@Service
public class EduQuestionOptionServiceImpl extends
        ServiceImpl<EduQuestionOptionMapper, EduQuestionOption>
        implements EduQuestionOptionService {

    @Resource
    private EduQuestionOptionMapper questionOptionMapper;

    @Override
    public void deleteByQuestionId(Long id) {
        questionOptionMapper.deleteByQuestionId(id);
    }

    @Override
    public List<EduQuestionOption> getByQuestionId(Long id) {
        List<EduQuestionOption> options = questionOptionMapper.selectList(
                new LambdaQueryWrapper<EduQuestionOption>().eq(EduQuestionOption::getQuestionId,
                        id));
        return options;
    }
}




