package org.shiloh.app.config.security;

import org.shiloh.app.config.security.handler.MyAuthenticationFailureHandler;
import org.shiloh.app.config.security.handler.MyAuthenticationSuccessHandler;
import org.shiloh.app.validate.smscode.SmsAuthenticationConfig;
import org.shiloh.app.validate.smscode.SmsCodeFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:58
 * @description 自定义资源服务器配置
 */
@Configuration
@EnableResourceServer // 开启资源服务器
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final MyAuthenticationSuccessHandler successHandler;

    private final MyAuthenticationFailureHandler failureHandler;

    private final SmsCodeFilter smsCodeFilter;

    private final SmsAuthenticationConfig smsAuthenticationConfig;

    public MyResourceServerConfig(MyAuthenticationSuccessHandler successHandler, MyAuthenticationFailureHandler failureHandler,
                                  SmsCodeFilter smsCodeFilter, SmsAuthenticationConfig smsAuthenticationConfig) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.smsCodeFilter = smsCodeFilter;
        this.smsAuthenticationConfig = smsAuthenticationConfig;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 添加短信验证码校验过滤器
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 使用表单登录替代默认的登录方式
                .formLogin()
                // 设置处理表单登录的url
                .loginProcessingUrl("/login")
                // 注入登录成功的处理器
                .successHandler(successHandler)
                // 注入登录失败的处理器
                .failureHandler(failureHandler)
                .and()
                // 授权配置
                .authorizeRequests()
                // 开发无需认证的接口
                .antMatchers("/login", "/code/sms_code", "/sms_login")
                .permitAll()
                // 所有请求都需要经过认证才能访问
                .anyRequest()
                .authenticated()
                .and()
                // 关闭CSRF攻击防御
                .csrf()
                .disable()
                .apply(smsAuthenticationConfig);
    }
}
