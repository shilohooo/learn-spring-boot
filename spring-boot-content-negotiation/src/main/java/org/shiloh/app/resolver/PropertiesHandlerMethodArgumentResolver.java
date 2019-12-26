package org.shiloh.app.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 11:03
 * @description 通过实现HandlerMethodArgumentResolver的方式来将HTTP请求体的内容自动解析为Properties对象
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 指定支持解析的参数类型
     * @return boolean
     **/
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Properties.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        String contentType = request.getHeader("Content-Type");
        MediaType mediaType = MediaType.parseMediaType(contentType);
        // 获取编码
        Charset charset = mediaType.getCharset() == null ? StandardCharsets.UTF_8 : mediaType.getCharset();
        // 获取输入流
        InputStream is = request.getInputStream();
        InputStreamReader reader = new InputStreamReader(is, charset);
        // 输入流转换为properties
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }
}
