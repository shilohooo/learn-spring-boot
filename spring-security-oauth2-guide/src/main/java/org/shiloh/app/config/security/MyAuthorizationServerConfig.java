package org.shiloh.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:38
 * @description 自定义认证服务器配置
 * //@Order(Ordered.HIGHEST_PRECEDENCE) // 确保认证服务器比资源服务器先配置，避免出现Full authentication is required to access this resource的问题
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务器
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private static final String OTHER_CLIENT_ID = "shiloh02";

    private static final String OTHER_CLIENT_SECRET = "shiloh59502";

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 指定认证管理器和处理用户登录认证逻辑的service
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // client id
                .withClient(clientId)
                // client secret，需要进行加密处理
                .secret(new BCryptPasswordEncoder().encode(clientSecret))
                // token有效时间，单位：秒
                .accessTokenValiditySeconds(3600)
                // token有效期的刷新时间，单位：秒
                // 也就是说在这864000秒内都可以通过refresh_token来换取新的令牌；
                .refreshTokenValiditySeconds(864000)
                // 作用域，若传递其他作用域值则会出错
                .scopes("all", "a", "b", "c")
                // 授权类型，限制只能通过以密码的方式获取token
                .authorizedGrantTypes("password")
                .and()
                // 配置第二个客户端
                .withClient(OTHER_CLIENT_ID)
                .secret(new BCryptPasswordEncoder().encode(OTHER_CLIENT_SECRET))
                .accessTokenValiditySeconds(7200);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
