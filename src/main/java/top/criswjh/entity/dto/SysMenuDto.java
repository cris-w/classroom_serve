package top.criswjh.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author wjh
 * @date 2021/12/6 8:16 下午
 */
@Data
public class SysMenuDto implements Serializable {

    private Long id;
    private String name;
    private String title;
    private String icon;
    private String path;
    private String component;
    private List<SysMenuDto> children = new ArrayList<>();

}
