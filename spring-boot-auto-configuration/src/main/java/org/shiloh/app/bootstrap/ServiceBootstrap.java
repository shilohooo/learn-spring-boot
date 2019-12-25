package org.shiloh.app.bootstrap;

import org.shiloh.app.service.TestService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:43
 * @description
 */
@ComponentScan("org.shiloh.app.service")
public class ServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        TestService testService = context.getBean("testService", TestService.class);
        System.out.println("testService = " + testService);
        context.close();
    }
}
