package org.shiloh.app.config;

import org.shiloh.app.entity.Fish;
import org.shiloh.app.entity.Test;
import org.shiloh.app.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author lxlei
 * @date Created in 2020/1/17 15:25
 * @description
 */
@Configuration
public class WebConfig {

    /**
     * 注册该组件，并指定初始化和销毁方法
     * @author lxlei
     * @return org.shiloh.app.entity.User
     * @date 2020/1/17 15:25
     **/
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    public Test test() {
        return new Test();
    }

    @Bean
    public Fish fish() {
        return new Fish();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
