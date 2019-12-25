package org.shiloh.web.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;
import org.shiloh.web.config.UserDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Shiloh
 * @Date 2019/11/28 17:16
 * @Description TODO
 */
@Data
@Accessors(chain = true)
//@JsonIgnoreProperties({"name", "sex"}) // 用于序列化时忽略一组属性
// 指定一个自定义的序列化类来序列化user实体
//@JsonSerialize(using = UserSerializer.class)
// 指定一个自定义的反序列化类来将某个字段反序列为user实体中的属性
@JsonDeserialize(using = UserDeserializer.class)
public class User implements Serializable {

    /**
     * 带有@JsonView注解且value为UsernameView.class的属性
     * 在控制器层获取user信息的方法中如果同样加了@JsonView且注解的value值一样
     * 那么序列化user实体时只会序列化那些带有@JsonView(UsernameView.class)注解的属性
     */
    public interface UsernameView {}
    public interface AllUserFieldsView extends UsernameView {}

    /**
     * JsonProperty 用于为JSON key指定一个别名
     * JsonView 用于定义一个序列化组，可作用于属性或类上
     */
//    @JsonProperty("name")
    @JsonView(UsernameView.class)
    private String username;

//    @JsonProperty("sex")
    @JsonView(AllUserFieldsView.class)
    private String gender;

    @JsonView(AllUserFieldsView.class)
    private Integer age;

    /**
     * @JsonIgnore 用于序列化时忽略某个属性
     * @JsonFormat 用于日志格式化：pattern 日期格式化的格式，timezone 时区
     */
//    @JsonIgnore
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonView(AllUserFieldsView.class)
    private Date birthday;
}
