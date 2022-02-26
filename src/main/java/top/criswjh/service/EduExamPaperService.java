package top.criswjh.service;

import top.criswjh.entity.EduExamPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.vo.exam.PaperVo;

/**
* @author wjh
*/
public interface EduExamPaperService extends IService<EduExamPaper> {

    /**
     * 通过试卷id获取问题列表
     *
     * @param id
     * @return
     */
    PaperVo getQuestionListById(Long id);
}
