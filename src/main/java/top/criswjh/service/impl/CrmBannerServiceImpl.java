package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import top.criswjh.entity.CrmBanner;
import top.criswjh.service.CrmBannerService;
import top.criswjh.mapper.CrmBannerMapper;
import org.springframework.stereotype.Service;

/**
* @author wjh
*/
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
    implements CrmBannerService{

    @Resource
    private CrmBannerMapper bannerMapper;

    @Cacheable(value = "banner", key = "'HotBanner'")
    @Override
    public List<CrmBanner> getHotBanner() {

        List<CrmBanner> list = bannerMapper.getHotBanner();
        return list;
    }
}




