package top.criswjh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Date;
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
import top.criswjh.entity.EduStudentQuestion;
import top.criswjh.entity.bo.edu.StudentPaperBo;
import top.criswjh.entity.vo.exam.StudentExamVo;
import top.criswjh.service.EduStudentExamService;
import top.criswjh.service.EduStudentQuestionService;

/**
 * @author wjh
 * @date 2022/3/12 4:11 PM
 */
@RestController
@RequestMapping("/edu/studentExam")
public class EduStudentExamController {

    @Resource
    private EduStudentExamService studentExamService;
    @Resource
    private EduStudentQuestionService eduStudentQuestionService;

    /**
     * 获取所有学生考试列表
     *
     * @return list
     */
    @GetMapping("/list")
    public AjaxResult<List<EduStudentExam>> list() {
        List<EduStudentExam> list = studentExamService.list();
        return AjaxResult.success(list);
    }

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

    /**
     * 通过试卷id 和 班级Id 获取学生考试记录
     *
     * @param paperId 试卷ID
     * @param classId 班级ID
     * @return list
     */
    @GetMapping("/listExamById/{paperId}/{classId}")
    public AjaxResult<List<StudentExamVo>> listExamById(@PathVariable("paperId") Long paperId,
            @PathVariable("classId") Long classId) {
        List<StudentExamVo> list = studentExamService.listExamById(paperId, classId);
        return AjaxResult.success(list);
    }

    /**
     * 通过学生ID 和 试卷ID 获取考试题目信息
     *
     * @param studentId 学生ID
     * @param paperId   试卷ID
     * @return list
     */
    @GetMapping("/listQuestionById/{studentId}/{paperId}")
    public AjaxResult<List<EduStudentQuestion>> listQuestionById(
            @PathVariable("studentId") Long studentId,
            @PathVariable("paperId") Long paperId) {
        List<EduStudentQuestion> list = eduStudentQuestionService.listQuestionById(studentId,
                paperId);
        return AjaxResult.success(list);
    }

    /**
     * 通过ID修改学生考试题目分数
     *
     * @param question question
     * @return ok
     */
    @PostMapping("/updateStudentQuestion")
    public AjaxResult<Void> updateStudentQuestion(@RequestBody EduStudentQuestion question) {
        eduStudentQuestionService.updateById(question);
        return AjaxResult.success("修改成功");
    }

    /**
     * 修改学生考试信息（批阅试卷）
     *
     * @param exam exam
     * @return ok
     */
    @PostMapping("/updateStudentExam")
    public AjaxResult<Void> updateStudentExam(@RequestBody EduStudentExam exam) {
        exam.setReadTime(new Date());
        studentExamService.updateById(exam);
        return AjaxResult.success("修改成功");
    }

    /**
     * 通过试卷ID 和 学生ID 删除相关考试相关记录
     *
     * @param paperId 试卷ID
     * @param studentId 学生ID
     * @return ok
     */
    @GetMapping("/remove/{paperId}/{studentId}")
    public AjaxResult<Void> removeById(@PathVariable("paperId") Long paperId,
            @PathVariable("studentId") Long studentId) {
        studentExamService.removeByPaperIdAndStudentId(paperId, studentId);
        return AjaxResult.success("删除成功");
    }
}
