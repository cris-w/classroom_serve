package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduStudentClass;
import top.criswjh.service.EduCourseService;
import top.criswjh.service.EduStudentClassService;

/**
 * @author wjh
 * @date 2022/3/10 1:57 PM
 */
@RestController
@RequestMapping("/studentClass")
public class EduStudentClassController {

    @Resource
    private EduStudentClassService studentClassService;
    @Resource
    private EduCourseService eduCourseService;

    /**
     * 通过学生id查询加入班级id
     *
     * @param id id
     * @return classId
     */
    @GetMapping("/getClassByStudentId/{id}")
    public AjaxResult<Long> getClassByStudentId(@PathVariable Long id) {
        EduStudentClass one = studentClassService.getOne(
                new LambdaQueryWrapper<EduStudentClass>()
                        .eq(EduStudentClass::getStudentId, id));
        if (one == null) {
            // 如果没有加入班级返回 -1
            return AjaxResult.success(-1L);
        }
        return AjaxResult.success(one.getClassId());
    }

    /**
     * 学生加入班级
     *
     * @param studentClass
     * @return
     */
    @PostMapping("/joinClass")
    public AjaxResult<Void> joinClass(@RequestBody EduStudentClass studentClass) {
        // 加入班级
        studentClassService.save(studentClass);
        // 更新课程人数
        eduCourseService.updateCourseCount(studentClass.getClassId());
        return AjaxResult.success("加入成功");
    }
}
