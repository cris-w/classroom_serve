package top.criswjh.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.bo.CourseInfoBo;
import top.criswjh.service.EduCourseService;

/**
 * @author wjh
 * @date 2022/1/6 6:44 PM
 */
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
}
