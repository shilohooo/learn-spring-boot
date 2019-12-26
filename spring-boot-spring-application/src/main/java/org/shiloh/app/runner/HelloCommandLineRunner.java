package org.shiloh.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 9:33
 * @description
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("HelloCommandLineRunner.run:hello command line runner");
    }
}
