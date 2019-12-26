package org.shiloh.app.controller;

import org.shiloh.app.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 14:09
 * @description
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @GetMapping("/test/async")
    public String testAsyncMethod() {
        try {
            long start = System.currentTimeMillis();
            logger.info("异步方法开始执行");
            Future<String> future = testService.asyncTest01();
            // Future接口的get方法用于获取异步调用的返回值
            // get还有一个get(long timeout, TimeUnit unit)重载方法，可以通过这个重载方法设置超时时间，
            // 即异步方法在设定时间内没有返回值的话，直接抛出java.util.concurrent.TimeoutException异常。
            String result = future.get(60, TimeUnit.SECONDS);
            logger.info("异步方法返回值：{}", result);
            logger.info("异步方法执行结束");
            logger.info("总耗时：{} ms", (System.currentTimeMillis() - start));
            return result;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.error("异步方法执行异常：[{}]", e.getMessage());
            return "异步方法执行异常！";
        }
    }

    @GetMapping("/test/sync")
    public void testSyncMethod() {
        long start = System.currentTimeMillis();
        logger.info("同步方法开始执行");
        testService.syncTest();
        logger.info("同步方法执行结束");
        logger.info("总耗时：{} ms", (System.currentTimeMillis() - start));
    }
}
