package org.shiloh.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lxlei
 * @date Created in 2020/1/16 9:27
 * @description 认证服务器配置，统一发放令牌及校验
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用默认登录页面
        http.formLogin()
                .and()
                .authorizeRequests()
                // 所有的请求都需要认证
                .anyRequest()
                .authenticated();
    }
}
