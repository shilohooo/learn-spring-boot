package org.shiloh;

import org.shiloh.app.config.WebConfig;
import org.shiloh.app.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author shiloh
 */
@SpringBootApplication
public class SpringBeanRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBeanRegisterApplication.class, args);
        // 测试获取注册的user实体
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        final User user = context.getBean(User.class);
        System.out.println("user = " + user);
        // 查看容器中已注册的bean的命名
        final String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }

}
