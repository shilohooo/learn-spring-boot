package org.shiloh.app.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 14:13
 * @description
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * 全局异常处理：
     * 捕捉AuthorizationException，跳转到未授权页面
     *
     * @return java.lang.String
     **/
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException() {
        return "403";
    }

}
