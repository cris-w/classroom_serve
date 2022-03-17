package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
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
public class EduStudentQuestionServiceImpl extends
        ServiceImpl<EduStudentQuestionMapper, EduStudentQuestion>
        implements EduStudentQuestionService {

    @Resource
    private EduStudentQuestionMapper eduStudentQuestionMapper;

    @Override
    public List<EduStudentQuestion> listQuestionById(Long studentId, Long paperId) {

        return eduStudentQuestionMapper.selectList(
                new LambdaQueryWrapper<EduStudentQuestion>().eq(EduStudentQuestion::getStudentId,
                        studentId).eq(EduStudentQuestion::getPaperId, paperId));
    }
}




