package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionKnowledge;
import top.criswjh.service.EduKnowledgePointService;
import top.criswjh.service.EduQuestionKnowledgeService;
import top.criswjh.mapper.EduQuestionKnowledgeMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【edu_question_knowledge(题目-知识点关联表)】的数据库操作Service实现
 * @createDate 2022-01-23 17:36:23
 */
@Service
public class EduQuestionKnowledgeServiceImpl extends
        ServiceImpl<EduQuestionKnowledgeMapper, EduQuestionKnowledge>
        implements EduQuestionKnowledgeService {

    @Resource
    private EduQuestionKnowledgeMapper questionKnowledgeMapper;
    @Resource
    private EduKnowledgePointService knowledgePointService;

    @Override
    public List<EduKnowledgePoint> listKnowledgeById(Long id) {
        return questionKnowledgeMapper.listQuestionById(id);
    }

    @Override
    public void deleteByQuestionId(Long id) {
        questionKnowledgeMapper.deleteByQuestionId(id);
    }

    @Override
    public List<EduKnowledgePoint> getByQuestionId(Long id) {
        // 通过问题id 获取 知识点列表
        List<EduQuestionKnowledge> knowledgeList = questionKnowledgeMapper.selectList(
                new LambdaQueryWrapper<EduQuestionKnowledge>().eq(
                        EduQuestionKnowledge::getQuestionId, id));
        // 将知识点列表转为 知识点id列表
        List<Long> ids = knowledgeList.stream().map(EduQuestionKnowledge::getKnowledgeId)
                .collect(Collectors.toList());

        List<EduKnowledgePoint> eduKnowledgePoints = knowledgePointService.listByIds(ids);
        return eduKnowledgePoints;
    }
}




