package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduStudentExam;
import top.criswjh.entity.bo.edu.StudentPaperBo;
import top.criswjh.service.EduStudentExamService;

/**
 * @author wjh
 * @date 2022/3/12 4:11 PM
 */
@RestController
@RequestMapping("/edu/studentExam")
public class EduStudentExamController {

    @Resource
    private EduStudentExamService studentExamService;

    /**
     * 通过学生ID 获取该学生考过的所有试卷
     *
     * @param studentId 学生ID
     * @return list
     */
    @GetMapping("/listByStudentId/{studentId}")
    public AjaxResult<List<EduStudentExam>> listByStudentId(@PathVariable Long studentId) {
        List<EduStudentExam> list = studentExamService.list(
                new LambdaQueryWrapper<EduStudentExam>().eq(
                        EduStudentExam::getStudentId, studentId));
        return AjaxResult.success(list);
    }

    /**
     * 新增考试记录
     *
     * @param bo
     * @return
     */
    @PostMapping("/save")
    public AjaxResult<Void> save(@RequestBody StudentPaperBo bo) {
        studentExamService.savePaper(bo);
        return AjaxResult.success("操作成功");

    }
}
