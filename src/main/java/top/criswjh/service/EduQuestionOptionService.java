package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduQuestionOption;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
*/
public interface EduQuestionOptionService extends IService<EduQuestionOption> {

    /**
     * 通过题库表id 删除选项关联表信息
     *
     * @param id
     */
    void deleteByQuestionId(Long id);

    /**
     * 通过题库Id 获取所有相关的选项
     *
     * @param id 题库id
     * @return list
     */
    List<EduQuestionOption> getByQuestionId(Long id);
}
