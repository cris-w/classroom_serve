package top.criswjh.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.criswjh.common.lang.AjaxResult;
import top.criswjh.entity.SysRole;
import top.criswjh.entity.SysUser;

/**
 * @author wjh
 * @date 2021/12/3 6:35 下午
 */
@Api(tags = "权限模块")
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Resource
    Producer producer;

    @GetMapping("/captcha")
    public AjaxResult<Map<Object, Object>> captcha() throws IOException {
        String key = IdUtil.objectId();
        String code = producer.createText();

        // 用于测试
//        key = "123";
//        code = "123";

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        String str = "data:image/jpeg;base64,";
        String base64Img = str + Base64.encode(byteArrayOutputStream.toByteArray());

        redisCache.setCacheObject(key, code, 120, TimeUnit.SECONDS);
        return AjaxResult.success(MapUtil.builder()
                .put("key", key)
                .put("base64Img", base64Img)
                .build());
    }

    /**
     * 获取用户信息接口
     *
     * @param principal
     * @return
     */
    @GetMapping("/userInfo")
    public AjaxResult<Map<Object, Object>> userInfo(Principal principal) {
        SysUser user = sysUserService.getUserByName(principal.getName());

        // 获取该用户所有角色名
        List<SysRole> roleList = sysUserRoleService.listRolesByUserId(user.getId());
        List<String> roles = roleList.stream().map(SysRole::getCode).collect(Collectors.toList());

        return AjaxResult.success(
                MapUtil.builder()
                        .put("id", user.getId())
                        .put("username", user.getUsername())
                        .put("avatar", user.getAvatar())
                        .put("created", user.getCreated())
                        .put("email", user.getEmail())
                        .put("statu", user.getStatu())
                        .put("roles", roles)
                        .build());
    }
}
