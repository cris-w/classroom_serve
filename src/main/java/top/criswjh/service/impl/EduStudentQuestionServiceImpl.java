package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.EduStudentQuestion;
import top.criswjh.mapper.EduStudentQuestionMapper;
import top.criswjh.service.EduStudentQuestionService;
import org.springframework.stereotype.Service;

/**
* @author wjh
* @description 针对表【edu_sutdent_question(学生-试题关联表)】的数据库操作Service实现
* @createDate 2022-01-22 23:41:11
*/
@Service
public class EduStudentQuestionServiceImpl extends ServiceImpl<EduStudentQuestionMapper, EduStudentQuestion>
    implements EduStudentQuestionService {

}




