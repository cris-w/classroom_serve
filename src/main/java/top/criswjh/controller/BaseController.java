package top.criswjh.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import top.criswjh.common.redis.RedisCache;

/**
 * @author wjh
 * @date 2021/12/3 6:37 下午
 */
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisCache redisCache;
}
