package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.EduQuestionKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
* @description 针对表【edu_question_knowledge(题目-知识点关联表)】的数据库操作Service
* @createDate 2022-01-23 17:36:24
*/
public interface EduQuestionKnowledgeService extends IService<EduQuestionKnowledge> {

    /**
     * 通过题库id查询关联知识点
     *
     * @param id 题库id
     * @return
     */
    List<EduKnowledgePoint> listKnowledgeById(Long id);

    /**
     * 通过题库id删除关联知识点信息
     *
     * @param id id
     */
    void deleteByQuestionId(Long id);

    /**
     * 通过问题id获取所有关联知识点
     *
     * @param id 问题id
     * @return knowledgeList
     */
    List<EduKnowledgePoint> getByQuestionId(Long id);

}
