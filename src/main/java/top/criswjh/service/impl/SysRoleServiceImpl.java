package top.criswjh.service.impl;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.function.BiFunction;
import javax.annotation.Resource;
import top.criswjh.entity.SysRole;
import top.criswjh.mapper.SysRoleMapper;
import top.criswjh.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
* @author wjh
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2021-12-05 14:31:28
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
implements SysRoleService{

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public Boolean nameExist(String name) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        return sysRoleMapper.selectCount(wrapper.eq(SysRole::getName, name)) > 0;
    }

    @Override
    public Boolean codeExist(String code) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        return sysRoleMapper.selectCount(wrapper.eq(SysRole::getCode, code)) > 0;
    }
}
