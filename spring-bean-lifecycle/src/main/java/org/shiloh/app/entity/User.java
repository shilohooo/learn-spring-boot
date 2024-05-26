package org.shiloh.app.entity;

/**
 * @author lxlei
 * @date Created in 2020/1/17 15:22
 * @description
 */
public class User {

    public User() {
        System.out.println("调用无参构造器创建user bean");
    }

    public void init() {
        System.out.println("初始化user...");
    }

    public void destroy() {
        System.out.println("销毁user实例...");
    }
}
