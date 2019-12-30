package org.shiloh.app.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 9:48
 * @description 自定义验证码类型异常
 */
public class MyValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -5928925467542577416L;

    public MyValidateCodeException(String msg) {
        super(msg);
    }
}
