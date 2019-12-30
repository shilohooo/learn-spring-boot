package org.shiloh.app.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 21:56
 * @description 自定义模拟用户数据（参考SpringSecurity提供的UserDetails）
 */
@Data
public class User implements Serializable {

    private String username;

    private String password;

    private Boolean accountNonExpired = true;

    private Boolean accountNonLocked = true;

    private Boolean credentialsNonExpired = true;

    private Boolean enabled = true;
}
