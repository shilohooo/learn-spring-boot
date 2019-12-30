package org.shiloh.app.validate.sms.code;

import org.shiloh.app.config.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 11:48
 * @description 自定义短信登录认证流程配置
 */
@Component
public class SmsAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
        // 注入认证管理器
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        // 注入自定义登录成功的处理器
        filter.setAuthenticationSuccessHandler(successHandler);
        // 注入自定义登录失败的处理器
        filter.setAuthenticationFailureHandler(failureHandler);

        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        // 注入自定义user认证过程
        provider.setMyUserDetailsService(myUserDetailsService);
        // 将自定义的短信验证码校验过滤器配置到校验用户名和密码之后
        builder.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
