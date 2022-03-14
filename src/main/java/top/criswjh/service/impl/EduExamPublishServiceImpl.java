package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import top.criswjh.entity.EduExamPublish;
import top.criswjh.entity.bo.edu.ExamPublishBo;
import top.criswjh.entity.vo.exam.ExamPublishVo;
import top.criswjh.service.EduExamPublishService;
import top.criswjh.mapper.EduExamPublishMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【edu_exam_publish(发布考试表)】的数据库操作Service实现
 * @createDate 2022-02-26 15:16:38
 */
@Service
public class EduExamPublishServiceImpl extends ServiceImpl<EduExamPublishMapper, EduExamPublish>
        implements EduExamPublishService {

    @Resource
    private EduExamPublishMapper examPublishMapper;

    @Override
    public Boolean saveExam(ExamPublishBo bo) {
        List<Long> classIds = bo.getClassIds();
        if (!CollectionUtils.isEmpty(classIds)) {
            List<EduExamPublish> list = new ArrayList<>(classIds.size());
            classIds.forEach(id -> {
                EduExamPublish exam = new EduExamPublish();
                BeanUtils.copyProperties(bo, exam);
                exam.setClassId(id);
                list.add(exam);
            });
            return this.saveBatch(list);
        }
        return false;
    }

    @Override
    public List<ExamPublishVo> listPublishVo(String title, Long classId) {

        if (title != null) {
            return examPublishMapper.selectPublishVo(title.trim(), classId);
        }
        return examPublishMapper.selectPublishVo(null, classId);
    }
}




