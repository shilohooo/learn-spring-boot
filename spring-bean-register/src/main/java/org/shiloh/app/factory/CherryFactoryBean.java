package org.shiloh.app.factory;

import org.shiloh.app.entity.Cherry;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author lxlei
 * @date Created in 2020/1/17 14:44
 * @description
 */
public class CherryFactoryBean implements FactoryBean<Cherry> {
    /**
     * 获取需要注册的bean对象
     * @author lxlei
     * @return org.shiloh.app.entity.Cherry
     * @date 2020/1/17 14:45
     **/
    @Override
    public Cherry getObject() throws Exception {
        return new Cherry();
    }

    /**
     * 返回需要注册的bean对象类型
     * @author lxlei
     * @return java.lang.Class<?>
     * @date 2020/1/17 14:46
     **/
    @Override
    public Class<?> getObjectType() {
        return Cherry.class;
    }

    /**
     * 指定需要注册的bean是否为单例对象
     * @author lxlei
     * @return boolean
     * @date 2020/1/17 14:46
     **/
    @Override
    public boolean isSingleton() {
        return false;
    }
}
