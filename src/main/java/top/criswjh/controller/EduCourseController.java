package top.criswjh.controller;

import io.swagger.annotations.Api;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.bo.edu.CourseInfoBo;
import top.criswjh.entity.vo.edu.CourseInfoVo;
import top.criswjh.service.EduCourseService;

/**
 * @author wjh
 * @date 2022/1/6 6:44 PM
 */
@Api(tags = "课程管理模块")
@RestController
@RequestMapping("/edu/course")
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;

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
}
