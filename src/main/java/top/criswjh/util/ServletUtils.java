package top.criswjh.util;

import cn.hutool.extra.servlet.ServletUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wjh
 * @date 2021/12/2 9:14 下午
 */
public class ServletUtils extends ServletUtil {
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().print(string);
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }
}
