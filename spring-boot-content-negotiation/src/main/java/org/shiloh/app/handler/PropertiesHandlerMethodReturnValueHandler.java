package org.shiloh.app.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 11:22
 * @description 通过实现HandlerMethodReturnValueHandler的方式来自定义一个用于处理返回值类型为Properties类型的解析器
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return Properties.class.equals(methodParameter.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Properties properties = (Properties) returnValue;
        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;
        HttpServletResponse response = servletWebRequest.getResponse();
        ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
        // 获取请求头
        HttpHeaders headers = servletServerHttpResponse.getHeaders();
        // 获取Content-Type
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        // 获取请求体
        OutputStream body = servletServerHttpResponse.getBody();
        OutputStreamWriter writer = new OutputStreamWriter(body, charset);
        properties.store(writer, "Serialized by PropertiesHandlerMethodReturnValueHandler#handleReturnValue");
        // 通知Spring MVC请求已处理完毕
        // 这行代码告诉Spring，请求已经成功完成了，无需进行后续的处理
        modelAndViewContainer.setRequestHandled(true);
    }
}
