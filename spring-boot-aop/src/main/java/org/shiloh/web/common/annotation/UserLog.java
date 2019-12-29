package org.shiloh.web.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Shiloh
 * @Date 2019/11/28 11:06
 * @Description 用户操作日志注解，用于标注需要监控的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLog {
    /**
     * 操作信息
     *
     * @return
     */
    String value() default "";
}
