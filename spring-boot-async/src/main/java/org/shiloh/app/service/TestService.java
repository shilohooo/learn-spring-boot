package org.shiloh.app.service;

import java.util.concurrent.Future;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 12:26
 * @description
 */
public interface TestService {

    /**
     * 异步调用测试
     * @return void
     **/
    Future<String> asyncTest01();

    /**
     * 同步测试
     * @return void
     **/
    void syncTest();

    /**
     * 睡眠测试
     * @return void
     **/
    void sleep();
}
