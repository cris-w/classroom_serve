package top.criswjh.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wjh
 * @date 2021/12/2 10:40 下午
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                //可以用"redis://"来启用SSL连接
                .setAddress("redis://106.15.89.242:6379")
                .setPassword("wjh.0914");
        return Redisson.create(config);
    }
}
