package top.criswjh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.criswjh.entity.EduExamPublish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.criswjh.entity.vo.exam.ExamPublishVo;

/**
* @author wjh
* @description 针对表【edu_eaxm_publish(发布考试表)】的数据库操作Mapper
* @createDate 2022-02-26 15:16:38
* @Entity generate.domain.EduEaxmPublish
*/
public interface EduExamPublishMapper extends BaseMapper<EduExamPublish> {

    /**
     * 获取发布试卷信息
     *
     * @param title
     * @param classId
     * @return
     */
    List<ExamPublishVo> selectPublishVo(@Param("title") String title, @Param("classId") Long classId);
}




