package org.shiloh;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lxlei
 * @date Created in 2020/1/16 10:16
 * @description 单点登录客户端02
 */
@SpringBootApplication
@EnableOAuth2Sso
public class SsoClient02 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoClient02.class).run(args);
    }

}
