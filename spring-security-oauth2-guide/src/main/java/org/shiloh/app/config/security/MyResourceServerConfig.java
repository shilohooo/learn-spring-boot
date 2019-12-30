package org.shiloh.app.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:58
 * @description 自定义资源服务器
 */
@Configuration
@EnableResourceServer // 开启资源服务器
@Order(2)
public class MyResourceServerConfig {
}
