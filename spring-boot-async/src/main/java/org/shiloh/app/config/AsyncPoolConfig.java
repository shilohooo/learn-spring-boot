package org.shiloh.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 14:16
 * @description 自定义异步线程池配置（要使用该线程池，只需要在@Async注解上指定线程池Bean名称即可）
 */
@Configuration
public class AsyncPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程的数量，默认值为1
        executor.setCorePoolSize(20);
         // 设置线程池维护的最大线程数量，只有当核心线程都被用完并且缓冲队列满后，才会开始申请超过核心线程数的线程，默认值为Integer.MAX_VALUE
        executor.setMaxPoolSize(200);
        // 设置缓存队列
        executor.setQueueCapacity(25);
        // 设置超出核心线程数外的线程在空闲时候的最大存活时间，默认为60秒
        executor.setKeepAliveSeconds(200);
        // 设置线程名前缀
        executor.setThreadNamePrefix("asyncThread_");
        // 设置是否等待所有线程执行完毕才关闭线程池，默认值为false
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置waitForTasksToCompleteOnShutdown的等待的时长，默认值为0，即不等待
        executor.setAwaitTerminationSeconds(60);
        /**
         * 当没有线程可以被使用时的处理策略（拒绝任务），默认策略为abortPolicy，包含下面四种策略：
         * callerRunsPolicy：用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
         * abortPolicy：直接抛出java.util.concurrent.RejectedExecutionException异常。
         * discardOldestPolicy：当线程池中的数量等于最大线程数时、抛弃线程池中最后一个要执行的任务，并执行新传入的任务。
         * discardPolicy：当线程池中的数量等于最大线程数时，不做任何动作。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
