package org.shiloh.app.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lxlei
 * @date Created in 2020/1/17 15:37
 * @description
 */
public class Fish {

    public Fish() {
        System.out.println("调用无参构造器创建 fish bean");
    }

    @PostConstruct // 指定初始化bean的方法
    public void init() {
        System.out.println("初始化 fish bean");
    }

    @PreDestroy // 指定销毁bean的方法
    public void destroy() {
        System.out.println("销毁 fish bean");
    }
}
