package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.EduKnowledgePoint;
import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.vo.exam.KnowledgePointVo;

/**
* @author wjh
* @description 针对表【edu_knowledge_point(知识点表)】的数据库操作Service
*/
public interface EduKnowledgePointService extends IService<EduKnowledgePoint> {

    /**
     * 返回所有知识点
     *
     * @return list
     */
    List<KnowledgePointVo> getKnowledgePointList();

    /**
     * 添加知识点
     * 注意：同一个父级不允许有重名
     *
     * @param point point
     */
    void addPoint(EduKnowledgePoint point);

    /**
     * 判断当前父节点下是否存在该节点
     *
     * @param title 节点名
     * @param parentId 父id
     * @return true 存在 false 不存在
     */
    boolean isExist(String title, Long parentId);

    /**
     * 修改知识点
     * 注意：同一个父级不允许有重名
     *
     * @param point
     */
    void edit(EduKnowledgePoint point);
}
