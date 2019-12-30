package org.shiloh.app.config;

import org.shiloh.app.handler.MyAuthenticationFailureHandler;
import org.shiloh.app.handler.MyAuthenticationSuccessHandler;
import org.shiloh.app.validate.code.MyValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 21:31
 * @description 自定义SpringSecurity配置：基于表单的认证
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.httpBasic() HTTP Basic方式
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器，且在校验用户名和密码之前执行
                .formLogin()// 认证方式：基于表单的认证
                .loginPage("/authentication/require") // 自定义登录页面，替换SpringSecurity的默认界面
                .loginProcessingUrl("/login") // 登录请求url
                .successHandler(authenticationSuccessHandler) // 登录成功的处理器
                .failureHandler(authenticationFailureHandler) // 登录失败的处理器
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        "/login.html",
                        "/code/image.jpg").permitAll() // 无需认证的请求url
                .anyRequest().authenticated() // 除去antMatchers()方法配置的请求路径以外，所有请求url都需要认证
                .and().csrf().disable(); // 关闭CSRF攻击防御
    }

    /**
     * 配置密码加密实现
     * @author lxlei
     * @return org.springframework.security.crypto.password.PasswordEncoder
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}