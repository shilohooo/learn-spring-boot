package org.shiloh;

import org.shiloh.app.config.WebConfig;
import org.shiloh.app.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lxlei
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        // 返回IOC容器，使用注解配置，传入配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        System.out.println("容器创建完毕...");
        //final User user = context.getBean(User.class);
        // 关闭IOC容器
        context.close();
    }

}
