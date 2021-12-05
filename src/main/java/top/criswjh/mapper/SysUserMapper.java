package top.criswjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;

/**
* @author wjh
*/
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 通过 userid 获取 导航菜单信息
     * @param userId id
     * @return nav
     */
    List<Long> getNavMenuIds(Long userId);
}
