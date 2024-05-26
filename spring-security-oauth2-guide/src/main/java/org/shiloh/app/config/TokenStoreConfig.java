package org.shiloh.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author shiloh
 * @Date Created in 2020/1/2 15:54
 * @description token存储配置
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    public RedisConnectionFactory factory;

    //@Bean
    /*public TokenStore redisTokenStore() {
        return new RedisTokenStore(factory);
    }*/
}
