package top.criswjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import top.criswjh.common.exception.MyException;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.vo.exam.KnowledgePointVo;
import top.criswjh.service.EduKnowledgePointService;
import top.criswjh.mapper.EduKnowledgePointMapper;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【edu_knowledge_point(知识点表)】的数据库操作Service实现
 */
@Service
public class EduKnowledgePointServiceImpl extends
        ServiceImpl<EduKnowledgePointMapper, EduKnowledgePoint>
        implements EduKnowledgePointService {

    @Resource
    private EduKnowledgePointMapper knowledgePointMapper;

    @Override
    public List<KnowledgePointVo> getKnowledgePointList() {
        List<EduKnowledgePoint> points = knowledgePointMapper.selectList(null);
        List<KnowledgePointVo> list = this.buildTree(points);
        return list;
    }

    @Override
    public void addPoint(EduKnowledgePoint point) {
        if (isExist(point.getTitle(), point.getParentId())) {
            throw new MyException(500, "知识点已经存在");
        }

        point.setGmtCreate(Date.from(Instant.now()));
        knowledgePointMapper.insert(point);
    }

    @Override
    public boolean isExist(String title, Long parentId) {
        return knowledgePointMapper.selectCount(
                new LambdaQueryWrapper<EduKnowledgePoint>()
                        .and(
                                wrapper -> wrapper.eq(EduKnowledgePoint::getParentId, parentId))
                        .and(
                                wrapper -> wrapper.eq(EduKnowledgePoint::getTitle, title.trim())
                        )) > 0;
    }

    @Override
    public void edit(EduKnowledgePoint point) {
        EduKnowledgePoint old = knowledgePointMapper.selectById(point.getId());
        if (isExist(point.getTitle(), point.getParentId()) && !old.getTitle().equals(point.getTitle())) {
            throw new MyException(500, "知识点已经存在");
        }

        point.setGmtUpdate(Date.from(Instant.now()));
        knowledgePointMapper.updateById(point);
    }

    /**
     * 将知识点列表构建为树形结构
     *
     * @param points 知识点列表
     * @return tree
     */
    private List<KnowledgePointVo> buildTree(List<EduKnowledgePoint> points) {
        List<KnowledgePointVo> tree = new ArrayList<>();
        // 将 EduKnowledgePoint -> KnowledgePointVo
        List<KnowledgePointVo> Vos = points.stream().map(c -> {
            KnowledgePointVo v = new KnowledgePointVo();
            v.setId(c.getId());
            v.setTitle(c.getTitle());
            v.setParentId(c.getParentId());
            return v;
        }).collect(Collectors.toList());

        Vos.forEach(point -> {
            // 获取所有parentID 为 当前ID的子集
            List<KnowledgePointVo> child = Vos.stream()
                    .filter(p -> point.getId().equals(p.getParentId()))
                    .collect(Collectors.toList());

            point.setChildren(child);

            // 如果当前节点的parentID 为 0 则代表顶级目录，添加到list中
            if (point.getParentId() == 0) {
                tree.add(point);
            }
        });

        return tree;
    }


}




