package org.shiloh.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Shiloh
 * @Date 2019/11/29 12:28
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;

    private String username;

    private String gender;

    private Integer age;

    private String address;

    private String email;
}
