package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduExamPublish;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.bo.edu.ExamPublishBo;
import top.criswjh.entity.vo.exam.ExamPublishVo;

/**
* @author wjh
* @description 针对表【edu_exam_publish(发布考试表)】的数据库操作Service
* @createDate 2022-02-26 15:16:38
*/
public interface EduExamPublishService extends IService<EduExamPublish> {

    /**
     * 发布试卷
     *
     * @param bo
     * @return
     */
    Boolean saveExam(ExamPublishBo bo);

    /**
     * 获取发布试卷信息
     *
     * @param title
     * @param classId
     * @return
     */
    List<ExamPublishVo> listPublishVo(String title, Long classId);

    /**
     * 通过id 删除发布试卷信息以及学生考试记录
     *
     * @param id id
     * @return true
     */
    Boolean removePaper(Long id);
}
