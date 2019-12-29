package org.shiloh.app.controller.exception;

import org.shiloh.app.exception.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 15:50
 * @description
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * @param e
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @ExceptionHandler 指定要处理的异常类型
     * @ResponseStatus 指定异常处理方法返回的HTTP状态码(500)
     **/
    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UserNotExistsException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", e.getId());
        data.put("msg", e.getMessage());
        return data;
    }
}
