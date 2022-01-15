package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.net.HttpRequest;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.EduCourse;
import top.criswjh.entity.SysUser;
import top.criswjh.entity.bo.edu.CourseInfoBo;
import top.criswjh.entity.vo.CoursePublishVo;
import top.criswjh.entity.vo.edu.CourseInfoVo;
import top.criswjh.service.EduCourseService;

/**
 * @author wjh
 * @date 2022/1/6 6:44 PM
 */
@Api(tags = "课程管理模块")
@RestController
@RequestMapping("/edu/course")
public class EduCourseController extends BaseController{

    @Resource
    private EduCourseService eduCourseService;

    /**
     * 模糊查询：通过课程名查询课程列表
     *
     * @param title
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<Page<EduCourse>> list(String title, Long current, Long size) {

        Page<EduCourse> pageData = eduCourseService.page(new Page<>(current, size),
                new QueryWrapper<EduCourse>().like(StrUtil.isNotBlank(title), "title",
                        title));

        return AjaxResult.success(pageData);
    }

    /**
     * 创建课程
     *
     * @param courseInfo info
     * @return info
     */
    @PostMapping("/addCourse")
    public AjaxResult<Long> addCourse(@RequestBody CourseInfoBo courseInfo) {

        Long id = eduCourseService.saveCourseInfo(courseInfo);
        return AjaxResult.success(id);
    }

    /**
     * 通过ID获取课程信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseInfo/{courseId}")
    public AjaxResult<CourseInfoVo> getCourseInfo(@PathVariable Long courseId) {

        CourseInfoVo vo = eduCourseService.getCourseInfoById(courseId);
        return AjaxResult.success(vo);
    }

    /**
     * 更新课程信息
     *
     * @param courseInfoVo
     * @return
     */
    @PostMapping("/updateCourseInfo")
    public AjaxResult<Void> updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        eduCourseService.updateCourseInfo(courseInfoVo);
        return AjaxResult.success("更新成功");
    }

    /**
     * 删除课程信息：
     *   包括：课程信息、课程描述信息、章节信息、小节信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("/deleteCourse/{courseId}")
    public AjaxResult<Void> deleteCourse(@PathVariable Long courseId) {
        eduCourseService.deleteCourse(courseId);
        return AjaxResult.success("删除成功");
    }

    /**
     * 通过id获取 课程发布信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getCoursePublishInfo/{id}")
    public AjaxResult<CoursePublishVo> getCoursePublishInfo(@PathVariable Long id) {
        CoursePublishVo vo = eduCourseService.coursePublishInfo(id);
        return AjaxResult.success(vo);
    }

    /**
     * 发布课程：
     * 设置课程状态为 1
     *
     * @param courseId
     * @return
     */
    @PostMapping("/publishCourse/{courseId}")
    public AjaxResult<Void> publishCourse(@PathVariable Long courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(Const.STATUS_ON);
        course.setGmtUpdate(DateUtil.date());

        eduCourseService.updateById(course);
        return AjaxResult.success("发布成功");
    }
}
