package top.criswjh.mapper;

import java.util.List;
import top.criswjh.entity.CrmBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wjh
*/
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {

    /**
     * 获取当前热门的两条banner（sort 前二）
     *
     * @return
     */
    List<CrmBanner> getHotBanner();

}




