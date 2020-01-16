package org.shiloh.app.config;

import org.shiloh.app.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxlei
 * @date Created in 2020/1/16 18:22
 * @description
 */
@Configuration
public class WebConfig {

    /**
     * 通过@Bean注解注册user实体到Spring容器中
     * @author lxlei
     * @return org.shiloh.app.entity.User
     * @date 2020/1/16 18:23
     **/
    @Bean("customUser")
    public User user() {
        return new User("shiloh", "male", 21);
    }
}
