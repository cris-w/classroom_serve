package top.criswjh.mapper;

import top.criswjh.entity.EduQuestionOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wjh
*/
public interface EduQuestionOptionMapper extends BaseMapper<EduQuestionOption> {

    /**
     * 通过题库表id 删除
     *
     * @param id
     */
    void deleteByQuestionId(Long id);
}




