package org.shiloh;

import org.shiloh.app.config.WebConfig;
import org.shiloh.app.entity.Cherry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shiloh
 */
@SpringBootApplication
public class SpringBeanRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBeanRegisterApplication.class, args);
        // 测试获取注册的user实体
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        // 观察Spring注册Bean的时机
        //System.out.println("Spring IOC容器创建完毕");
        //final User user = context.getBean(User.class);
        //System.out.println("user = " + user);
        // 查看容器中已注册的bean的命名
        //final String[] beanDefinitionNames = context.getBeanDefinitionNames();
        //Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        // 获取几次User实例，校验自定义组件扫描配置是否成功
        //Object user1 = context.getBean(User.class);
        //Object user2 = context.getBean(User.class);
        //System.out.println(user1 == user2);

        // 测试FactoryBean实现
        final Object cherry = context.getBean(Cherry.class);
        System.out.println("cherry.getClass() = " + cherry.getClass());
        /**
         * 获取CherryFactoryBean
         * 加&的原因：{@link org.springframework.beans.factory.BeanFactory.FACTORY_BEAN_PREFIX}
         */
        final Object cherryFactoryBean = context.getBean("&cherryFactoryBean");
        System.out.println("cherryFactoryBean.getClass() = " + cherryFactoryBean.getClass());

        // 测试@Profile注解
        //ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(SpringBeanRegisterApplication.class)
        //        .web(WebApplicationType.NONE)
        //        .profiles("java7")
        //        .run(args);
        //final CalculateService calculateService = configurableApplicationContext.getBean(CalculateService.class);
        //System.out.println("求和结果：" + calculateService.sum(1, 2, 3, 4, 5, 6, 7));
    }

}
