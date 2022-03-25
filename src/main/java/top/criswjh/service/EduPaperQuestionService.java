package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduPaperQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.vo.exam.QuestionBriefVo;

/**
* @author wjh
* @description 针对表【edu_paper_question(试卷-题目关联表)】的数据库操作Service
* @createDate 2022-01-22 23:41:11
*/
public interface EduPaperQuestionService extends IService<EduPaperQuestion> {

    /**
     * 通过题目Id 删除关联试卷信息
     *
     * @param ids
     * @return
     */
    Integer removeByPaperIds(List<Long> ids);

    /**
     * 通过试卷ID 获取题目信息。 (修改试卷的回显)
     *
     * @param paperId 试卷ID
     * @return list
     */
    List<QuestionBriefVo> listQuestionBriefVoById(Long paperId);
}
