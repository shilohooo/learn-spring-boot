package org.shiloh.app.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lxlei
 * @date Created in 2020/1/17 10:35
 * @description 定义bean注册的条件
 */
public class MyBeanRegisterConditionConfig implements Condition {

    /**
     * 定义往IOC容器注册bean的条件
     * @author lxlei
     * @param conditionContext 上下文信息
     * @param annotatedTypeMetadata 注解信息
     * @return boolean
     * @date 2020/1/17 11:09
     **/
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        final String osName = conditionContext.getEnvironment().getProperty("os.name");
        // 在Windows环境下，User这个组件将被成功注册，如果是别的操作系统，这个组件将不会被注册到IOC容器中
        return osName != null && osName.contains("Windows");
    }
}
