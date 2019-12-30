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
                // 指定没有权限访问时的处理器
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                // 添加验证码校验过滤器，且在校验用户名和密码之前执行
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 添加短信验证码校验过滤器
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 认证方式：基于表单的认证
                .formLogin()
                // 自定义登录页面，替换SpringSecurity的默认界面
                .loginPage("/authentication/require")
                // 登录请求url
                .loginProcessingUrl("/login")
                // 登录成功的处理器
                .successHandler(authenticationSuccessHandler)
                // 登录失败的处理器
                .failureHandler(authenticationFailureHandler)
                .and()
                // 开启记住我功能
                .rememberMe()
                // 指定token持久化仓库
                .tokenRepository(persistentTokenRepository())
                // 指定token有效时间（也就是记住我的失效时间），单位：秒
                .tokenValiditySeconds(3600)
                // 自定义处理登录逻辑
                .userDetailsService(myUserDetailsService)
                .and()
                // 授权配置
                .authorizeRequests()
                // 无需认证的请求url
                .antMatchers("/authentication/require",
                        "/login.html",
                        "/code/normal",
                        "/code/sms_code",
                        "/to/sms_login_page",
                        "/session_invalid",
                        "/sign_out/success").permitAll()
                // 除去antMatchers()方法配置的请求路径以外，所有请求url都需要认证
                .anyRequest().authenticated()
                .and()
                // 添加session管理器
                .sessionManagement()
                // 指定session失效后需要跳转的url
                .invalidSessionUrl("/session_invalid")
                // 配置session最大并发数量为1个，也就是同一个帐号在同一时刻只能登录1个
                .maximumSessions(1)
                // 当session达到最大有效数时，不再允许登录相同的用户
                .maxSessionsPreventsLogin(true)
                // 指定session失效策略
                .expiredSessionStrategy(mySessionExpiredStrategy)
                .and()
                .and()
                .logout()
                // 指定退出登录的url，默认为/logout
                .logoutUrl("/sign_out")
                // 退出登录成功后跳转的url，默认跳转到登录页面
                //.logoutSuccessUrl("/sign_out/success")
                // 指定退出成功的处理器
                .logoutSuccessHandler(logOutSuccessHandler)
                // 退出后将key=JSESSIONID的cookie删除
                .deleteCookies("JSESSIONID")
                // 关闭CSRF攻击防御
                .and().csrf().disable()
                // 将短信验证码校验配置加入到SpringSecurity中
                .apply(smsAuthenticationConfig);
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
