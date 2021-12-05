package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.criswjh.entity.SysUser;
import top.criswjh.mapper.SysUserMapper;
import top.criswjh.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2021-12-05 14:31:28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

}
