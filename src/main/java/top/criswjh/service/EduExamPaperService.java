package top.criswjh.service;

import top.criswjh.entity.EduExamPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.PaperBo;
import top.criswjh.entity.vo.exam.PaperVo;

/**
* @author wjh
*/
public interface EduExamPaperService extends IService<EduExamPaper> {

    /**
     * 通过试卷id获取问题列表
     *
     * @param id id
     * @return paperVo
     */
    PaperVo getQuestionListById(Long id);

    /**
     * 创建考试
     *
     * @param paperBo bo
     */
    void createPaper(PaperBo paperBo);


}
