package top.criswjh.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用于读取Excel生成SysUser的实体
 *
 * @author wjh
 * @date 2022/1/5 9:38 PM
 */
@Data
public class UserDto {

    @ExcelProperty(index = 0)
    private String username;
}
