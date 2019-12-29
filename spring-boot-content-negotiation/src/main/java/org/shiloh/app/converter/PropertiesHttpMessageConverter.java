package org.shiloh.app.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 10:36
 * @description
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }

    /**
     * 序列化响应内容
     *
     * @return void
     **/
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        // 获取请求头
        HttpHeaders headers = httpOutputMessage.getHeaders();
        // 获取Content-Type
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        // 获取请求体
        OutputStream body = httpOutputMessage.getBody();
        OutputStreamWriter writer = new OutputStreamWriter(body, charset);
        properties.store(writer, "Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    /**
     * 反序列化http请求
     *
     * @return java.util.Properties
     **/
    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        // 获取请求头
        HttpHeaders headers = httpInputMessage.getHeaders();
        // 获取Content-Type
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        // 获取请求体
        InputStream body = httpInputMessage.getBody();
        InputStreamReader reader = new InputStreamReader(body, charset);
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, httpInputMessage);
    }
}
