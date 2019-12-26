package org.shiloh.app.common.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:53
 * @description 全局异常处理器
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * 统一处理请求参数非法异常（普通传参）
     * @param e
     * @return java.lang.String
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        Path path;
        String[] pathArray;
        for (ConstraintViolation<?> violation : violations) {
            path = violation.getPropertyPath();
            pathArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            builder.append(pathArray[1])
                    .append(violation.getMessage())
                    .append(",");
        }
        builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        return builder.toString();
    }

    /**
     * 统一处理非法请求参数（实体对象传参）
     * @param e
     * @return java.lang.String
     **/
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(org.springframework.validation.BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField())
                    .append(error.getDefaultMessage())
                    .append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return message.toString();
    }
}
