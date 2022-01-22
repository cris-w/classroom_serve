package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.EduStudentClass;
import top.criswjh.service.EduStudentClassService;
import top.criswjh.mapper.EduStudentClassMapper;
import org.springframework.stereotype.Service;

/**
* @author wjh
* @description 针对表【edu_student_class(学生班级关联表)】的数据库操作Service实现
* @createDate 2022-01-22 23:41:11
*/
@Service
public class EduStudentClassServiceImpl extends ServiceImpl<EduStudentClassMapper, EduStudentClass>
    implements EduStudentClassService{

}




