package top.criswjh.mapper;

import java.util.List;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wjh
* @description 针对表【edu_question_knowledge(题目-知识点关联表)】的数据库操作Mapper
* @createDate 2022-01-23 17:36:24
* @Entity generate.domain.EduQuestionKnowledge
*/
public interface EduQuestionKnowledgeMapper extends BaseMapper<EduQuestionKnowledge> {

    /**
     * 通过题库id查询知识点
     *
     * @param id 题目id
     * @return
     */
    List<EduKnowledgePoint> listQuestionById(Long id);
}




