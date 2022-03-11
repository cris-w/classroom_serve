package top.criswjh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.criswjh.entity.EduStudentVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wjh
 * @description 针对表【edu_student_video(学生-视频关联表)】的数据库操作Mapper
 * @createDate 2022-03-11 13:57:02
 * @Entity generate.domain.EduStudentVideo
 */
public interface EduStudentVideoMapper extends BaseMapper<EduStudentVideo> {

    /**
     * 通过学生ID 和 课程ID 查询学生已经观看的 视屏ID
     *
     * @param studentId
     * @param courseId
     * @return
     */
    List<Long> selectStudentVideoList(@Param("studentId") Long studentId,
            @Param("courseId") Long courseId);

    /**
     * 查询记录是否存在
     *
     * @param studentId
     * @param courseId
     * @param videoId
     * @return
     */
    Integer selectExist(@Param("studentId") Long studentId, @Param("courseId") Long courseId,
            @Param("videoId") Long videoId);
}




