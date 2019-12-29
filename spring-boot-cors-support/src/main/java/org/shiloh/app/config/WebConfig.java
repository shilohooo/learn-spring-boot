package org.shiloh.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 12:15
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 跨域配置
     *
     * @param registry
     * @return void
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问所有请求
        registry.addMapping("/**")
                // 允许所有origin
                .allowedOrigins("*")
                // 允许使用get请求跨域访问该服务器的资源
                .allowedMethods("GET");
    }
}
