package org.shiloh.app.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxlei
 * @date Created in 2020/1/16 9:35
 * @description
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
