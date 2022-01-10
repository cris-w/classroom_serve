package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduClass;
import top.criswjh.service.EduClassService;

/**
 * @author wjh
 * @date 2022/1/7 2:09 AM
 */
@Api(tags = "班级管理模块")
@RestController
@RequestMapping("/edu/class")
public class EduClassController {

    @Resource
    private EduClassService eduClassService;

    /**
     * 获取所有班级列表 并 排序
     *
     * @return list
     */
    @GetMapping("/getClassList")
    public AjaxResult<List<EduClass>> getClassList() {
        List<EduClass> list = eduClassService.list(new QueryWrapper<EduClass>().orderByAsc("sort"));
        return AjaxResult.success(list);
    }
}
