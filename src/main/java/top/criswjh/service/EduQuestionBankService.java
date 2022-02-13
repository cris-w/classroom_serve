package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduQuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
