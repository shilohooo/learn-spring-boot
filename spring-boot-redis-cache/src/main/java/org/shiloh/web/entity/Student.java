package org.shiloh.web.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/28 15:43
 * @Description TODO
 */
@Entity
@Table(name = "tb_student_02")
@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = " varchar(50) ")
    private String name;

    @Column(name = "gender", columnDefinition = " tinyint(1) ")
    private String gender;

    @Column(name = "age", columnDefinition = " int(11) ")
    private Integer age;
}
