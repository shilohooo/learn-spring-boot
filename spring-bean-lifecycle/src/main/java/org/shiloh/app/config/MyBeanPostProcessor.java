package org.shiloh.app.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author lxlei
 * @date Created in 2020/1/17 15:40
 * @description
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在组件的初始化方法调用之前执行
     * @author lxlei
     * @param bean 当前组件对象
     * @param beanName 当前组件在容器中的名称
     * @return java.lang.Object
     * @date 2020/1/17 15:41
     **/
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " bean初始化之前调用的方法...");
        return bean;
    }

    /**
     * 在组件的初始化方法调用之后执行
     * @author lxlei
     * @param bean 当前组件对象
     * @param beanName 当前组件在容器中的名称
     * @return java.lang.Object
     * @date 2020/1/17 15:41
     **/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " bean初始化之后调用的方法");
        return bean;
    }
}
