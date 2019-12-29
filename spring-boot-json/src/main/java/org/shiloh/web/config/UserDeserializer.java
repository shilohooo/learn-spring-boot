package org.shiloh.web.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.shiloh.web.entity.User;

import java.io.IOException;

/**
 * @Author Shiloh
 * @Date 2019/11/29 9:22
 * @Description 自定义反序列化类
 */
public class UserDeserializer extends JsonDeserializer<User> {
    /**
     * 自定义反序列化规则
     * 反序列user-name为user实体的username属性
     *
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String username = node.get("user-name").asText();
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
