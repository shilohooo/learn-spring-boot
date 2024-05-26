package org.shiloh.app.config.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:38
 * @description 自定义认证服务器配置
 * @Order(Ordered.HIGHEST_PRECEDENCE) // 确保认证服务器比资源服务器先配置，
 * 避免出现Full authentication is required to access this resource的问题
 */
@Configuration
// 开启认证服务器
@EnableAuthorizationServer
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsServiceImpl myUserDetailsService;

    private static final String OTHER_CLIENT_ID = "shiloh02";

    private static final String OTHER_CLIENT_SECRET = "shiloh59502";

    private final TokenStore jwtTokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final TokenEnhancer tokenEnhancer;

    public MyAuthorizationServerConfig(AuthenticationManager authenticationManager, MyUserDetailsServiceImpl myUserDetailsService,
                                       TokenStore jwtTokenStore, JwtAccessTokenConverter jwtAccessTokenConverter,
                                       @Qualifier("jwtTokenEnhancer") TokenEnhancer tokenEnhancer) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenStore = jwtTokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenEnhancer = tokenEnhancer;
    }

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer);
        tokenEnhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(tokenEnhancers);
        // 指定认证管理器和处理用户登录认证逻辑的service
        endpoints.authenticationManager(authenticationManager)
                // 指定令牌存储策略
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                // 指定处理用户认证细节的service实现类
                .userDetailsService(myUserDetailsService)
                // token增强
                .tokenEnhancer(enhancerChain);
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
                // 授权类型，密码方式、刷新令牌方式获取token
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                // 配置第二个客户端
                .withClient(OTHER_CLIENT_ID)
                .secret(new BCryptPasswordEncoder().encode(OTHER_CLIENT_SECRET))
                .accessTokenValiditySeconds(7200);
    }
}
