package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.common.lang.Const;
import top.criswjh.entity.EduCourse;
import top.criswjh.entity.bo.edu.CourseInfoBo;
import top.criswjh.entity.vo.CoursePublishVo;
import top.criswjh.entity.vo.TeacherVo;
import top.criswjh.entity.vo.course.CourseInfoVo;
import top.criswjh.entity.vo.course.CourseVo;

/**
 * @author wjh
 * @date 2022/1/6 6:44 PM
 */
@Api(tags = "课程管理模块")
@RestController
@RequestMapping("/edu/course")
public class EduCourseController extends BaseController {

    /**
     * 查询热门课程和名师接口 （查询参加人数前八的课程，以及前id前四的教师） 学生端使用
     *
     * @return list
     */
    @GetMapping("/index")
    public AjaxResult<Map<Object, Object>> index() {
        // 查询热度前8的课程
        List<EduCourse> list = eduCourseService.getHotList();
        // 查询id前4的教师
        List<TeacherVo> teachers = sysUserRoleService.listHotUser("教师");
        return AjaxResult.success(MapUtil.builder()
                .put("courseList", list)
                .put("teacherList", teachers)
                .build());
    }

    /**
     * 通过班级Id查询 已发布的课程列表
     *
     * @param classId 班级Id
     * @return list
     */
    @GetMapping("/getCourseByClassId/{classId}")
    public AjaxResult<List<CourseVo>> getCourseByClassId(@PathVariable Long classId) {
//        List<EduCourse> list = eduCourseService.list(new LambdaQueryWrapper<EduCourse>().eq(
//                EduCourse::getClassId, classId));
        List<CourseVo> list = eduCourseService.listByClassId(classId);
        List<CourseVo> res = list.stream().filter(l -> l.getStatus().equals(Const.STATUS_ON))
                .collect(Collectors.toList());
        return AjaxResult.success(res);
    }


    /**
     * 模糊查询：通过课程名查询课程列表
     *
     * @param title title
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
     * 删除课程信息： 包括：课程信息、课程描述信息、章节信息、小节信息
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
     * 发布课程： 设置课程状态为 1
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
