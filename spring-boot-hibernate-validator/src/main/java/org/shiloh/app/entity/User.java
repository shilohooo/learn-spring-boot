package org.shiloh.app.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 10:03
 * @description
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 3574621376147999986L;

    @NotBlank(message = "{required}")
    private String username;

    @Email(message = "{invalid}")
    private String email;
}
