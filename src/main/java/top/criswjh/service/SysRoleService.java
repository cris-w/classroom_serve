package top.criswjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.criswjh.entity.SysRole;

/**
 * @author wjh
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 判断插入时name 或 唯一编码 字段是否存在
     *
     * @param name name
     * @param code code
     * @return 0 都不存在  1 name存在  2 code存在
     */
    Integer nameExist(String name, String code);

}
