package org.shiloh.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:25
 * @description 用户实体，此实体主要替代spring security自带的user
 */
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 3574621376147999986L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号是否未过期
     */
    private Boolean accountNonExpired = true;

    /**
     * 帐号是否未被锁定
     */
    private Boolean accountNonLocked = true;

    /**
     * 密码凭证是否未过期
     */
    private Boolean credentialsNonExpired = true;

    /**
     * 用户是否启用
     */
    private Boolean enabled = true;
}
