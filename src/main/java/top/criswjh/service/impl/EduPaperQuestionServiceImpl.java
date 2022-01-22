package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.EduPaperQuestion;
import top.criswjh.service.EduPaperQuestionService;
import top.criswjh.mapper.EduPaperQuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author wjh
* @description 针对表【edu_paper_question(试卷-题目关联表)】的数据库操作Service实现
* @createDate 2022-01-22 23:41:11
*/
@Service
public class EduPaperQuestionServiceImpl extends ServiceImpl<EduPaperQuestionMapper, EduPaperQuestion>
    implements EduPaperQuestionService{

}




