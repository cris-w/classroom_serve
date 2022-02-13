package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionKnowledge;
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

    @Override
    public List<EduKnowledgePoint> listKnowledgeById(Long id) {
        return questionKnowledgeMapper.listQuestionById(id);
    }
}




