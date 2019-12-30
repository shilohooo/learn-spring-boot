package org.shiloh.app.config;

import org.shiloh.app.handler.MyAuthenticationAccessDeniedHandler;
import org.shiloh.app.handler.MyAuthenticationFailureHandler;
import org.shiloh.app.handler.MyAuthenticationSuccessHandler;
import org.shiloh.app.handler.MyLogOutSuccessHandler;
import org.shiloh.app.session.MySessionExpiredStrategy;
import org.shiloh.app.validate.code.MyValidateCodeFilter;
import org.shiloh.app.validate.sms.code.SmsAuthenticationConfig;
import org.shiloh.app.validate.sms.code.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 21:31
 * @description 自定义SpringSecurity配置：基于表单的认证
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启SpEL表达式授权注解
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyValidateCodeFilter validateCodeFilter;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private MySessionExpiredStrategy mySessionExpiredStrategy;

    @Autowired
    private MyLogOutSuccessHandler logOutSuccessHandler;

    @Autowired
    private MyAuthenticationAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.httpBasic() HTTP Basic方式
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) // 指定没有权限访问时的处理器
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器，且在校验用户名和密码之前执行
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加短信验证码校验过滤器
                .formLogin()// 认证方式：基于表单的认证
                .loginPage("/authentication/require") // 自定义登录页面，替换SpringSecurity的默认界面
                .loginProcessingUrl("/login") // 登录请求url
                .successHandler(authenticationSuccessHandler) // 登录成功的处理器
                .failureHandler(authenticationFailureHandler) // 登录失败的处理器
                .and()
                .rememberMe() // 开启记住我功能
                .tokenRepository(persistentTokenRepository()) // 指定token持久化仓库
                .tokenValiditySeconds(3600) // 指定token有效时间（也就是记住我的失效时间），单位：秒
                .userDetailsService(myUserDetailsService) // 自定义处理登录逻辑
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        "/login.html",
                        "/code/normal",
                        "/code/sms_code",
                        "/to/sms_login_page",
                        "/session_invalid",
                        "/sign_out/success").permitAll() // 无需认证的请求url
                .anyRequest().authenticated() // 除去antMatchers()方法配置的请求路径以外，所有请求url都需要认证
                .and()
                .sessionManagement() // 添加session管理器
                .invalidSessionUrl("/session_invalid") // 指定session失效后需要跳转的url
                .maximumSessions(1) // 配置session最大并发数量为1个，也就是同一个帐号在同一时刻只能登录1个
                .maxSessionsPreventsLogin(true) // 当session达到最大有效数时，不再允许登录相同的用户
                .expiredSessionStrategy(mySessionExpiredStrategy) // 指定session失效策略
                .and()
                .and()
                .logout()
                .logoutUrl("/sign_out") // 指定退出登录的url，默认为/logout
                //.logoutSuccessUrl("/sign_out/success") // 退出登录成功后跳转的url，默认跳转到登录页面
                .logoutSuccessHandler(logOutSuccessHandler) // 指定退出成功的处理器
                .deleteCookies("JSESSIONID") // 退出后将key=JSESSIONID的cookie删除
                .and().csrf().disable() // 关闭CSRF攻击防御
                .apply(smsAuthenticationConfig); // 将短信验证码校验配置加入到SpringSecurity中
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

    /**
     * 配置token持久化对象
     * @author lxlei
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     **/
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
