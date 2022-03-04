package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduQuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.QuestionBo;
import top.criswjh.entity.vo.exam.QuestionVo;

/**
* @author wjh
* @description 针对表【edu_question_bank(题库表)】的数据库操作Service
* @createDate 2022-01-22 23:41:11
*/
public interface EduQuestionBankService extends IService<EduQuestionBank> {

    /**
     * 获取所有类型的题
     *
     * @param type 0 单选 1 多选 2 主观
     * @param title 标题
     * @return
     */
    List<QuestionVo> listByType(Integer type, String title);

    /**
     * 通过ID 题库中的题目以及相关的选项
     *
     * @param id 问题id
     */
    void deleteQuestionById(Long id);

    /**
     * 创建题目
     *
     * @param bo questionBo
     */
    void addQuestion(QuestionBo bo);

    /**
     * 通过id获取questionVo
     *
     * @param id 问题id
     * @return Vo
     */
    QuestionVo getQuestionById(Long id);

    /**
     * 修改
     *
     * @param bo
     */
    void updateQuestion(QuestionBo bo);

    /**
     * 获取题目简要信息
     *
     * @param type 0 单选 1 多选 2 主观
     * @param title 标题
     * @return list
     */
    List<EduQuestionBank> listBrief(Integer type, String title);
}
