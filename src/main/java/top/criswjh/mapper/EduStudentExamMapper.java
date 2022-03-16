package top.criswjh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.criswjh.entity.EduStudentExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.criswjh.entity.vo.exam.StudentExamVo;

/**
* @author wjh
* @description 针对表【edu_student_exam(学生-试卷关联表)】的数据库操作Mapper
* @createDate 2022-01-22 23:41:11
* @Entity generate.domain.EduStudentExam
*/
public interface EduStudentExamMapper extends BaseMapper<EduStudentExam> {

    /**
     * 通过试卷ID 和 班级ID 获取考试记录
     *
     * @param paperId 试卷ID
     * @param classId 班级ID
     * @return list
     */
    List<StudentExamVo> selectExamById(@Param("paperId") Long paperId, @Param("classId") Long classId);
}




