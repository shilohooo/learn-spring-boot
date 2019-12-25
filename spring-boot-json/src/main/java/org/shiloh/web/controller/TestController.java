package org.shiloh.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shiloh.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/28 17:15
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private ObjectMapper mapper;

    /**
     * JsonView 测试序列列组的定义
     * UsernameView 只序列化username
     * AllUserFieldsView 所有字段
     * @return
     */
//    @JsonView(User.UsernameView.class)
    @JsonView(User.AllUserFieldsView.class)
    @GetMapping("/info")
    public User info() {
        User user = new User();
        user.setUsername("shiloh")
                .setGender("男")
                .setAge(21)
                .setBirthday(new Date());
        return user;
    }

    @GetMapping("/info/json")
    public String info2Str() {
        try {
            User user = new User();
            user.setUsername("monkey")
                    .setGender("male")
                    .setAge(22)
                    .setBirthday(new Date());
            // 将user对象中的数据序列化成json字符串并返回
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return String.format("serializer error:%1$s", e.getMessage());
        }
    }

    @GetMapping("/msg")
    public String readJsonStr() {
        // Jackson反序列化测试
        try {
            String jsonStr = "{\"username\":\"老李头\", \"gender\":\"male\", \"age\":21, \"birthday\":\"2019-11-28 17:30:00\"}";
            JsonNode node = mapper.readTree(jsonStr);
            String username = node.get("username").asText();
            int age = node.get("age").asInt();
            return String.format("姓名：%1$s，年龄：%2$s", username, age);
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("反序列失败：%1$s", e.getMessage());
        }
    }

    @GetMapping("/msg/multi")
    public String multiResolve() {
        try {
            // 解析多级JSON测试
            String jsonStr = "{ \"username\": \"shiloh\", \"age\": 22, \"hobby\": {\"first\": \"film\", \"second\": \"game\"} }";
            JsonNode node = mapper.readTree(jsonStr);
            JsonNode hobby = node.get("hobby");
            String firstHobby = hobby.get("first").asText();
            return String.format("第一个爱好是：%1$s", firstHobby);
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("解析多级Json字符串失败：%1$s", e.getMessage());
        }
    }

    @GetMapping("/bind")
    public User readJsonAsObject() {
        try {
            // Jackson将json字符串绑定到对象测试
            String jsonStr = "{\"username\":\"老李头\", \"gender\":\"male\", \"age\":21, \"birthday\":\"2019-11-28 17:30:00\"}";
            return mapper.readValue(jsonStr, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("绑定失败：%1$s", e.getMessage()));
        }
    }

    @GetMapping("/deserializer")
    public String testDeserializer() {
        try {
            // 测试自定义反序列类
            String jsonStr = "{\"user-name\": \"老李头\"}";
            User user = mapper.readValue(jsonStr, User.class);
            return user.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("反序列化username属性失败：%1$s", e.getMessage());
        }
    }

    /**
     * 集合的反序列
     * Spring Boot 能自动识别出List对象包含的是User类，
     * 因为在方法中定义的泛型的类型会被保留在字节码中，
     * 所以Spring Boot能识别List包含的泛型类型从而能正确反序列化。
     * @param users
     * @return
     */
    @PostMapping("/params-size")
    public Integer getParamsSize(@RequestBody List<User> users) {
        return users.size();
    }

    /**
     * 此方法中readValue没有指定集合泛型，在反序列时会抛出ClassCastException
     * 因为在运行时刻，泛型己经被擦除了（不同于方法参数定义的泛型，不会被擦除）
     * 为了提供泛型信息，Jackson提供了JavaType ，用来指明集合类型。
     * 请看下面一个方法{@link TestController#fixCustomize()}
     * @return
     */
    @GetMapping("/customize")
    public String customize() {
        try {
            String jsonStr = "[ { \"user-name\":\"shiloh\", \"gender\":\"male\" }, { \"user-name\":\"jack\", \"gender\":\"male\" } ]";
            List<User> users;
            users = mapper.readValue(jsonStr, List.class);
            StringBuilder msg = new StringBuilder();
            for (User user : users) {
                msg.append(user.getUsername()).append(",");
            }
            return msg.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("反序列失败：%1$s", e.getMessage());
        }
    }

    @GetMapping("/fix-customize")
    public String fixCustomize() {
        try {
            String jsonStr = "[ { \"user-name\":\"shiloh\", \"gender\":\"male\" }, { \"user-name\":\"jack\", \"gender\":\"male\" } ]";
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
            List<User> users = mapper.readValue(jsonStr, type);
            StringBuilder msg = new StringBuilder();
            for (User user : users) {
                msg.append(user.getUsername()).append(",");
            }
            return msg.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("反序列失败：%1$s", e.getMessage());
        }
    }
}
