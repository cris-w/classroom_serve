package top.criswjh.service;

import java.util.List;
import top.criswjh.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author wjh
*/
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询热门banner
     *
     * @return list
     */
    List<CrmBanner> getHotBanner();
}
