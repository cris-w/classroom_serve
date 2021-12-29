package top.criswjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUserRole;
import top.criswjh.mapper.SysRoleMapper;
import top.criswjh.mapper.SysUserRoleMapper;
import top.criswjh.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author wjh
 * @description 针对表【sys_user_role】的数据库操作Service实现
 * @createDate 2021-12-05 14:31:28
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> listRolesByUserId(Long id) {
        return sysRoleMapper.getUserRolesById(id);
    }
}
