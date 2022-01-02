package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.SysRole;

/**
 * @author wjh
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 判断插入时name 字段是否存在
     *
     * @param name name
     * @return true 存在   false 不存在
     */
    Boolean nameExist(String name);

    /**
     * 判断插入 唯一编码 字段是否存在
     * @param code
     * @return true 存在   false 不存在
     */
    Boolean codeExist(String code);

}
