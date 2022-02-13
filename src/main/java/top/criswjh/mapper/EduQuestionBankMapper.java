package top.criswjh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.criswjh.entity.EduQuestionBank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wjh
* @description 针对表【edu_question_bank(题库表)】的数据库操作Mapper
* @createDate 2022-01-22 23:41:11
* @Entity generate.domain.EduQuestionBank
*/
public interface EduQuestionBankMapper extends BaseMapper<EduQuestionBank> {

    /**
     * 模糊查询题目
     *
     * @param type 0 单选 1 多选 2 主观
     * @param title 标题
     * @return
     */
    List<EduQuestionBank> listByType(@Param("type") Integer type, @Param("title") String title);

}




