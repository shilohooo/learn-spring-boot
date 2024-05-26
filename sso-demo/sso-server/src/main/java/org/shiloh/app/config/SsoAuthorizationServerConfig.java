package org.shiloh.app.config;

import org.shiloh.app.common.service.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author lxlei
 * @date Created in 2020/1/16 9:49
 * @description 单点登录认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    private final CustomUserDetailServiceImpl customUserDetailService;

    public SsoAuthorizationServerConfig(PasswordEncoder passwordEncoder,
                                        CustomUserDetailServiceImpl customUserDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("shiloh");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 配置第一个客户端
                .withClient("client01")
                // 客户端密钥
                .secret(this.passwordEncoder.encode("client01-123456"))
                // grant types，登录方式使用的是SpringSecurity默认的，需要开启authorization_code认证类型支持
                .authorizedGrantTypes("refresh_token", "authorization_code")
                // 令牌有效时长
                .accessTokenValiditySeconds(3600)
                // 作用域
                .scopes("all")
                // 登录成功后自动授权，无需点击
                .autoApprove(true)
                // 重定向uri
                .redirectUris("http://127.0.0.1:9090/client01/login")
                .and()
                .withClient("client02")
                .secret(this.passwordEncoder.encode("client02-123456"))
                .authorizedGrantTypes("refresh_token", "authorization_code")
                .accessTokenValiditySeconds(7200)
                .scopes("all")
                .autoApprove(true)
                .redirectUris("http://127.0.0.1:9091/client02/login");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置获取密钥时需要进行身份证验证
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(customUserDetailService);
    }
}
