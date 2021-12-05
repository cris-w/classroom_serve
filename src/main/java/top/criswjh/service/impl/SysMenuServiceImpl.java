package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.SysMenu;
import top.criswjh.mapper.SysMenuMapper;
import top.criswjh.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2021-12-05 14:31:28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

}
