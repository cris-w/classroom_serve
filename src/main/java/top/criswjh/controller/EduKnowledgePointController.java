package top.criswjh.controller;

import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduKnowledgePoint;
import top.criswjh.entity.vo.exam.KnowledgePointVo;
import top.criswjh.service.EduKnowledgePointService;

/**
 * @author wjh
 * @date 2022/1/24 9:29 PM
 */
@Api(tags = "知识点模块")
@RestController
@RequestMapping("/edu/knowledgePoint")
public class EduKnowledgePointController {

    @Resource
    private EduKnowledgePointService knowledgePointService;

    /**
     * 获取知识点列表
     *
     * @return list
     */
    @GetMapping("/list")
    public AjaxResult<List<KnowledgePointVo>> list() {
        List<KnowledgePointVo> list = knowledgePointService.getKnowledgePointList();
        return AjaxResult.success(list);
    }

    /**
     * 通过ID 获取知识点
     *
     * @param id id
     * @return point
     */
    @GetMapping("/getById/{id}")
    public AjaxResult<EduKnowledgePoint> getById(@PathVariable Long id) {
        EduKnowledgePoint point = knowledgePointService.getById(id);
        return AjaxResult.success(point);
    }


    /**
     * 添加知识点
     *
     * @param point point
     * @return ok
     */
    @PostMapping("/save")
    public AjaxResult<Void> save(@RequestBody EduKnowledgePoint point) {
        knowledgePointService.addPoint(point);

        return AjaxResult.success("添加成功");
    }

    /**
     * 修改知识点
     *
     * @param point point
     * @return ok
     */
    @PostMapping("/update")
    public AjaxResult<Void> update(@RequestBody EduKnowledgePoint point) {

        knowledgePointService.edit(point);
        return AjaxResult.success("修改成功");
    }

    /**
     * 通过ids 批量删除知识点
     *
     * @param ids ids
     * @return ok
     */
    @PostMapping("/delete")
    public AjaxResult<Void> delete(@RequestBody List<Long> ids) {
        knowledgePointService.removeByIds(ids);

        return AjaxResult.success("删除成功");
    }
}
