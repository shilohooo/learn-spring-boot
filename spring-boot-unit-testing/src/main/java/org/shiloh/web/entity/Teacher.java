package org.shiloh.web.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:33
 * @Description TODO
 */
@Entity
@Table(name = "tb_teacher")
@org.hibernate.annotations.Table(appliesTo = "tb_teacher", comment = "教师信息表")
@Data
@Accessors(chain = true)
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = " varchar(50) comment '姓名' ")
    private String name;

    @Column(name = "gender", columnDefinition = " tinyint(1) comment '性别：1男2女' ")
    private Integer gender;

    @Column(name = "age", columnDefinition = " int(11) comment '年龄' ")
    private Integer age;
}
