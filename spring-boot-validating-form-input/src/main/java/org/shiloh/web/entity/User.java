package org.shiloh.web.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/26 17:18
 * @Description TODO
 */
@Table(name = "tb_user")
@org.hibernate.annotations.Table(appliesTo = "tb_user", comment = "用户信息表")
@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 5, max = 20, message = "用户名长度必须为5-20字符")
    @Column(name = "username", columnDefinition = " varchar(50) comment '用户名' ")
    private String username;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 120, message = "年龄不能大于120")
    @Column(name = "age", columnDefinition = " int(11) comment '年龄' ")
    private Integer age;

    @NotEmpty(message = "出生日期不能为空")
    @Column(name = "birthday", columnDefinition = " varchar(50) comment '出生日期' ")
    private String birthday;
}
