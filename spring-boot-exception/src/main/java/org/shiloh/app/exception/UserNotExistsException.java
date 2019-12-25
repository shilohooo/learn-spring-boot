package org.shiloh.app.exception;

import lombok.Data;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 15:45
 * @description
 */
@Data
public class UserNotExistsException extends RuntimeException {
    private static final long serialVersionUID = -2571845593997596767L;

    private Long id;

    public UserNotExistsException(Long id) {
        super("user not exists");
        this.id = id;
    }
}
