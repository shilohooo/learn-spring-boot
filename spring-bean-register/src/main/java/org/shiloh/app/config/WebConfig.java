package org.shiloh.app.config;

import org.shiloh.app.common.MyImportBeanDefinitionRegistrar;
import org.shiloh.app.entity.User;
import org.shiloh.app.factory.CherryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lxlei
 * @date Created in 2020/1/16 18:22
 * @description
 */
@Configuration
//@ComponentScan(basePackages = {"org.shiloh.app"},
//        // 通过@Filter注解来排除一些组件的扫描注册
//        excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,// 根据注解来排除
//                // 指定要排除的注解类型
//                classes = {RestController.class, Repository.class}),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,// 根据指定类类型排除
//                // 排除类类型为User的组件，其子类与实现类都会被排除
//                classes = User.class)
//        }) // 扫描组件并注册
//@ComponentScan(basePackages = {"org.shiloh.app"},
//        // 指定要扫描的组件
//        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
//                // 用useDefaultFilters = false来关闭Spring默认的扫描策略，让扫描的配置生效
//                classes = Service.class),
//        useDefaultFilters = false)
//@ComponentScan(basePackages = {"org.shiloh.app"}, excludeFilters = {
//        // 使用自定义的过滤器配置来指定排除组件扫描的策略
//        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)})
//@Import({Hello.class}) // 快速导入组件到IOC容器中
//@Import({MyImportSelector.class}) // 配置类的@Import注解上使用MyImportSelector来把组件快速地导入到IOC容器中
@Import({MyImportBeanDefinitionRegistrar.class}) // 手动往IOC容器注入组件
public class WebConfig {

    /**
     * 通过@Bean注解注册user实体到Spring容器中
     * @author lxlei
     * @return org.shiloh.app.entity.User
     * @date 2020/1/16 18:23
     **/
    @Bean("customUser")
    //@Lazy // 将bean注册改为懒加载模式
    // bean注册的条件
    @Conditional(MyBeanRegisterConditionConfig.class)
    public User user() {
        System.out.println("往Spring IOC容器中注册User bean");
        return new User("shiloh", "male", 21);
    }

    @Bean
    public CherryFactoryBean cherryFactoryBean() {
        return new CherryFactoryBean();
    }
}
