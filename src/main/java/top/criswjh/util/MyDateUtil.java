package top.criswjh.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author wjh
 * @date 2022/3/24 21:51
 */
@Component
public class MyDateUtil {
    public Map<Object, Object> getWeekList(String today) {
        Map<Object, Object> map = new LinkedHashMap<>(7);
        DateTime day = DateUtil.parse(today, "yyyy-MM-dd");
        for (int i = 6; i > 0; i--) {
            DateTime offsetDay = DateUtil.offsetDay(day, -i);
            map.put(DateUtil.format(offsetDay, "yyyy-MM-dd"), 0);
        }
        map.put(today, 0);
        return map;
    }
}
