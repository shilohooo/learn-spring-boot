package org.shiloh.app.service.impl;

import org.shiloh.app.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 12:26
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @return java.util.concurrent.Future
     * @Async 表明此方法是一个异步方法:
     * value：指定需要使用的线程池
     * 如果异步方法具有返回值的话，需要使用Future来接收回调值
     * 泛型指定返回值的类型，AsyncResult为Spring实现的Future实现类
     **/
    @Override
    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncTest01() {
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("Hello async~");
    }

    @Override
    public void syncTest() {
        sleep();
    }

    @Override
    public void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
