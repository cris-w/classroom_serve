package top.criswjh.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.CrmBanner;

/**
 * @author wjh
 * @date 2022/1/18 8:17 PM
 */
@Api("banner管理")
@RestController
@RequestMapping("/crm/banner")
public class CrmBannerController extends BaseController {

    /**
     * 模糊查询/分页查询：通过banner title查询banner列表
     *
     * @param title
     * @return page
     */
    @GetMapping("/list")
    public AjaxResult<Page<CrmBanner>> list(String title, Long current, Long size) {

        Page<CrmBanner> pageData = crmBannerService.page(new Page<>(current, size),
                new QueryWrapper<CrmBanner>().like(StrUtil.isNotBlank(title), "title",
                        title).orderByAsc("sort"));

        return AjaxResult.success(pageData);
    }

    /**
     * 查询热门的两条banner
     * 学生端使用
     *
     * @return list
     */
    @GetMapping("/getHotBanner")
    public AjaxResult<List<CrmBanner>> getHotBanner() {
        List<CrmBanner> list = crmBannerService.getHotBanner();
        return AjaxResult.success("查询成功", list);
    }

    /**
     * 添加banner
     *
     * @param crmBanner
     * @return ok
     */
    @PostMapping("/addBanner")
    public AjaxResult<Void> addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return AjaxResult.success("添加成功");
    }
}
