package top.criswjh.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduClass;

/**
 * @author wjh
 * @date 2022/1/7 2:09 AM
 */
@Api(tags = "班级管理模块")
@RestController
@RequestMapping("/edu/class")
public class EduClassController extends BaseController {


    /**
     * 模糊查询：通过课程名查询课程列表
     *
     * @param title
     * @return page
     */
    @GetMapping("/list")
    public AjaxResult<Page<EduClass>> list(String title, Long current, Long size) {

        Page<EduClass> pageData = eduClassService.page(new Page<>(current, size),
                new QueryWrapper<EduClass>().like(StrUtil.isNotBlank(title), "title",
                        title).orderByAsc("sort"));

        return AjaxResult.success(pageData);
    }

    /**
     * 获取所有班级列表 并 排序 （添加课程时使用）
     *
     * @return list
     */
    @GetMapping("/getClassList")
    public AjaxResult<List<EduClass>> getClassList() {
        List<EduClass> list = eduClassService.list(new QueryWrapper<EduClass>().orderByAsc("sort"));
        return AjaxResult.success(list);
    }

    /**
     * 通过ID 查询班级信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getClassInfo/{id}")
    public AjaxResult<EduClass> getClassInfo(@PathVariable Long id) {
        EduClass eduClass = eduClassService.getById(id);
        return AjaxResult.success(eduClass);
    }

    /**
     * 创建班级
     *
     * @param eduClass
     * @return ok
     */
    @PostMapping("/addClass")
    public AjaxResult<Void> addClass(@RequestBody EduClass eduClass) {
        if (eduClassService.createClass(eduClass)) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    /**
     * 修改班级信息
     *
     * @param eduClass
     * @return ok
     */
    @PostMapping("/updateClass")
    public AjaxResult<Void> updateClass(@RequestBody EduClass eduClass) {
        if (eduClassService.updateClass(eduClass)) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    /**
     * 通过ID 删除班级
     *
     * @param id
     * @return id
     */
    @GetMapping("/deleteClass/{id}")
    public AjaxResult<Long> deleteClass(@PathVariable Long id) {
        eduClassService.removeById(id);
        return AjaxResult.success("删除成功", id);
    }
}
