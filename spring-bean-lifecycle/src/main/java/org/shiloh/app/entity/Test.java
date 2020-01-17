package org.shiloh.app.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author lxlei
 * @date Created in 2020/1/17 15:34
 * @description
 */
public class Test implements InitializingBean, DisposableBean {

    public Test() {
        System.out.println("调用无参构造器创建test bean");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁test bean...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化test bean...");
    }
}
