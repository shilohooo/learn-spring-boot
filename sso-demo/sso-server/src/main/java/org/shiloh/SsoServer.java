package org.shiloh;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lxlei
 * @date Created in 2020/1/16 10:26
 * @description 单点登录服务端
 */
@SpringBootApplication
public class SsoServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoServer.class).run(args);
    }
}
