package org.shiloh.app.common;

import org.shiloh.app.entity.Strawberry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lxlei
 * @date Created in 2020/1/17 14:38
 * @description
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @author lxlei
     * @param annotationMetadata 可以通过它获取到类的注解信息
     * @param beanDefinitionRegistry Bean定义注册器，包含了一些和Bean有关的方法
     * @date 2020/1/17 14:39
     **/
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        final String beanName = "strawberry";
        // 通过BeanDefinitionRegistry的containsBeanDefinition方法判断IOC容器中是否包含了名称为strawberry的组件
        final boolean condition = beanDefinitionRegistry.containsBeanDefinition(beanName);
        if (!condition) {
            //如果没有，则手动通过BeanDefinitionRegistry的registerBeanDefinition方法注册一个
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Strawberry.class);
            beanDefinitionRegistry.registerBeanDefinition(beanName, rootBeanDefinition);
        }
    }
}
