package org.shiloh.app.common.config;

import org.shiloh.app.common.config.filter.TimeFilter;
import org.shiloh.app.common.config.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:11
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 通过FilterRegistrationBean来注册过滤器
     * FilterRegistrationBean除了注册过滤器TimeFilter外还通过setUrlPatterns方法配置了URL匹配规则
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     **/
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 通过InterceptorRegistry注册拦截器
     * @param registry
     * @return void
     **/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
