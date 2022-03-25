package top.criswjh.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.EduStudentVideo;
import top.criswjh.service.EduClassService;
import top.criswjh.service.EduCourseService;
import top.criswjh.service.EduExamPublishService;
import top.criswjh.service.EduStudentVideoService;
import top.criswjh.service.SysUserService;
import top.criswjh.util.MyDateUtil;

/**
 * 用于统计图标
 *
 * @author wjh
 * @date 2022/3/24 15:44
 */
@Api(tags = "ECharts统计")
@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private EduClassService eduClassService;
    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduExamPublishService eduExamPublishService;
    @Resource
    private EduStudentVideoService eduStudentVideoService;
    @Resource
    private MyDateUtil myDateUtil;

    /**
     * 获取数量信息
     *
     * @return map
     */
    @GetMapping("/getSysNums")
    public AjaxResult<Map<Object, Object>> getSysNums() {
        Long user = sysUserService.count();
        Long clazz = eduClassService.count();
        Long course = eduCourseService.count();
        // 活动目前只包括发布试卷，后期可能调整加上发布作业作业
        Long exam = eduExamPublishService.count();

        return AjaxResult.success(MapUtil.builder()
                .put("user", user)
                .put("clazz", clazz)
                .put("course", course)
                .put("exam", exam)
                .build());
    }

    /**
     * 获取最近七天的学生观看视频情况
     *
     * @return map
     */
    @GetMapping("/getVideoView")
    public AjaxResult<Map<Object, Object>> getVideoView() {
        String today = DateUtil.today();
        String last = DateUtil.format(DateUtil.offsetDay(new Date(), -6), "yyyy-MM-dd");
        List<EduStudentVideo> list = eduStudentVideoService.getVideoView(today, last);
        Map<Object, Object> weekMap = myDateUtil.getWeekList(today);
        list.forEach(l -> {
            String time = DateUtil.format(l.getWatchTime(), "yyyy-MM-dd");
            weekMap.put(time, Integer.parseInt(String.valueOf(weekMap.get(time))) + 1);
        });
        return AjaxResult.success(MapUtil.builder()
                .put("week", weekMap.keySet())
                .put("data", weekMap.values())
                .build());
    }

}
