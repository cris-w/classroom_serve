package top.criswjh.util;

import cn.hutool.core.lang.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wjh
 * @date 2021/12/2 9:09 下午
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static void init(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    public static String toJsonString(Object object) {
        if (Validator.isEmpty(object)) {
            return null;
        } else {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException var2) {
                throw new RuntimeException(var2);
            }
        }
    }

}
