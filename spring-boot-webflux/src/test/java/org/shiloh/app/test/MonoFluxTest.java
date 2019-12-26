package org.shiloh.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 15:25
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MonoFluxTest {
    @Test
    public void testMonoAndFlux() {
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(String.format("接收到数据：[%1$s]", integer));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("处理完了~~");
            }
        };

        String[] strArray = {"1", "2", "3"};
        Flux.fromArray(strArray).map(Integer::parseInt).subscribe(subscriber);
        Mono.fromSupplier(() -> 1).map(str -> str + 1).subscribe(subscriber);
    }
}
