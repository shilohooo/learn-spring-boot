package org.shiloh;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lxlei
 * @date Created in 2020/1/16 10:07
 * @description 单点登录客户端01
 */
@SpringBootApplication
@EnableOAuth2Sso // 开启单点登录支持
public class SsoClient01 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoClient01.class).run(args);
    }
}
