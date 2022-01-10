package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.vo.edu.ChapterVo;

/**
 * 课程Service
 *
* @author wjh
*/
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 通过课程ID查询章节和小节信息
     *
     * @param courseId id
     * @return list
     */
    List<ChapterVo> getChapterAndVideoByCourseId(Long courseId);

    /**
     * 删除章节信息，如果该章节包含小节则无法删除
     *
     * @param chapterId id
     * @return true 成功删除  false 该章节包含小节无法删除
     */
    boolean deleteById(Long chapterId);
}
