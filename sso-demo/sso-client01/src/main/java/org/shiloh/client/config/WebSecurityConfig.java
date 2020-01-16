package org.shiloh.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lxlei
 * @date Created in 2020/1/16 18:09
 * @description SpringSecurity配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启在请求之前的认证
// 认证服务器已经配置了Spring Security配置，并且顺序为100，和客户端的Spring Security配置冲突了。
// 所以这里修改下客户端的Spring Security配置顺序,让它的优先级小于认证服务器的Spring Security配置。
@Order(101)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
}
