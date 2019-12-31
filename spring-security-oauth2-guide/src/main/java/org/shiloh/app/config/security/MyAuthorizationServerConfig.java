package org.shiloh.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:38
 * @description 自定义认证服务器配置
 * //@Order(Ordered.HIGHEST_PRECEDENCE) // 确保认证服务器比资源服务器先配置，避免出现Full authentication is required to access this resource的问题
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务器
public class MyAuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
