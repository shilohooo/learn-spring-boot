package org.shiloh.app.config;

import org.shiloh.app.converter.PropertiesHttpMessageConverter;
import org.shiloh.app.handler.PropertiesHandlerMethodReturnValueHandler;
import org.shiloh.app.resolver.PropertiesHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 10:42
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @PostConstruct
    public void init() {
        // 将PropertiesHandlerMethodArgumentResolver添加到Spring自带的HandlerMethodArgumentResolver实现类集合中
        // 获取当前RequestMappingHandlerAdapter所有的ArgumentResolver对象
        List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
        // 添加PropertiesHandlerMethodArgumentResolver到newArgumentResolvers中的第一位
        newArgumentResolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
        // 将原有的ArgumentResolver添加到newArgumentResolvers
        newArgumentResolvers.addAll(argumentResolvers);
        // 重新设置ArgumentResolver对象集合
        adapter.setArgumentResolvers(newArgumentResolvers);

        // 将PropertiesHandlerMethodReturnValueHandler添加到到Spring自带的HandlerMethodReturnValueHandler实现类集合中
        // 获取当前RequestMappingHandlerAdapter所有的returnValueHandler对象
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        // 添加PropertiesHandlerMethodReturnValueHandler到newReturnValueHandlers中的第一位
        newReturnValueHandlers.add(0, new PropertiesHandlerMethodReturnValueHandler());
        // 将原有的returnValueHandler添加到newReturnValueHandlers中
        newReturnValueHandlers.addAll(returnValueHandlers);
        // 重新设置ReturnValueHandlers对象集合
        adapter.setReturnValueHandlers(newReturnValueHandlers);
    }

    /**
     * 将PropertiesHttpMessageConverter添加到HttpMessageConverter集合中
     * @param converters
     * @return void
     **/
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将PropertiesHttpMessageConverter定义为converters列表中的第一个HttpMessageConverter
        converters.add(0, new PropertiesHttpMessageConverter());

    }
}
