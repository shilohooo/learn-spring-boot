package org.shiloh.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxlei
 * @date Created in 2020/1/16 18:21
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String gender;

    private Integer age;

}
