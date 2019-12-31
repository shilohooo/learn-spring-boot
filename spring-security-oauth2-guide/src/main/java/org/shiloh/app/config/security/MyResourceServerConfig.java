package org.shiloh.app.config.security;

import org.shiloh.app.config.security.handler.MyAuthenticationFailureHandler;
import org.shiloh.app.config.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:58
 * @description 自定义资源服务器配置
 */
@Configuration
@EnableResourceServer // 开启资源服务器
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 使用表单登录替代默认的登录方式
        http.formLogin()
                // 设置处理表单登录的url
                .loginProcessingUrl("/login")
                // 注入登录成功的处理器
                .successHandler(successHandler)
                // 注入登录失败的处理器
                .failureHandler(failureHandler)
                .and()
                // 授权配置
                .authorizeRequests()
                // 所有请求都需要经过认证才能访问
                .anyRequest()
                .authenticated()
                .and()
                // 关闭CSRF攻击防御
                .csrf()
                .disable();
    }
}
