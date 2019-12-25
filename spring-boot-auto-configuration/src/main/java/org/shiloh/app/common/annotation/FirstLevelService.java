package org.shiloh.app.common.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:41
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface FirstLevelService {
    String value() default "";
}
