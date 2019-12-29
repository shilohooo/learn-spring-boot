package org.shiloh.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.shiloh.web.entity.User;

import java.io.IOException;

/**
 * @Author Shiloh
 * @Date 2019/11/29 9:13
 * @Description 自定义序列化类
 */
public class UserSerializer extends JsonSerializer<User> {

    /**
     * 自定义序列化规则
     * 将username序列化为user-name
     *
     * @param user
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name", user.getUsername());
        jsonGenerator.writeEndObject();
    }
}
