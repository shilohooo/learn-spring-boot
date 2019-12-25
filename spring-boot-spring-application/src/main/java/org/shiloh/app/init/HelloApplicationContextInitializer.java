package org.shiloh.app.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 18:17
 * @description
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("HelloApplicationContextInitializer: " + configurableApplicationContext.getId());
    }
}
