package top.criswjh.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.service.EduSubjectService;

/**
 * @author wjh
 * @date 2022/1/5 9:23 PM
 */
@RestController
@RequestMapping("/edu/subject")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    
}
