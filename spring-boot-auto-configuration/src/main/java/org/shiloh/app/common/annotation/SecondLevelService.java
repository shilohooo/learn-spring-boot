package org.shiloh.app.common.annotation;

import java.lang.annotation.*;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:46
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelService
public @interface SecondLevelService {
    String value() default "";
}
