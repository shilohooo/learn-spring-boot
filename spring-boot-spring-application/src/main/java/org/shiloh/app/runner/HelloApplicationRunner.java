package org.shiloh.app.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:32
 * @description
 */
@Component
public class HelloApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("HelloApplicationRunner.run:hello spring boot");
    }
}
