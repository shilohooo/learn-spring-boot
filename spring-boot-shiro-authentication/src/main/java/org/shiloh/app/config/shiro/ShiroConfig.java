package org.shiloh.app.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 15:51
 * @description shiro配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录后跳转的页面url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 设置未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 定义过滤器链
        // 注意的是filterChain基于短路机制，即最先匹配原则,必须使用LinkedHashMap
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // anon匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        // 开放登录接口
        filterChainDefinitionMap.put("/login", "anon");
        // druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 配置退出登录的过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        // 除去以上配置的无需拦截的url，其余所有资源都必须通过认证之后才能访问，未通过认证则会自动访问loginUrl
        // 基于表单的拦截器；如/**=authc，如果没有登录会跳到相应的登录页面登录
        //filterChainDefinitionMap.put("/**", "authc");
        // user指的是用户认证通过或者配置了Remember Me记住用户登录状态后可访问
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        // 配置securityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 注入cookie管理对象
        securityManager.setRememberMeManager(rememberMeManager());
        // 注入redisCacheManager
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        // 配置自定义实现shiroRealm
        return new MyShiroRealm();
    }

    // 配置记住我功能

    /**
     * shiro自带cookie对象，用于记住我功能
     * @return org.apache.shiro.web.servlet.SimpleCookie
     **/
    @Bean
    public SimpleCookie rememberMeCookie() {
        // 设置cookie名称，对应登录页面中的“记住我”复选框
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为30分钟
        simpleCookie.setMaxAge(1800);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 添加注解支持
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置redis缓存
     * @return org.crazycake.shiro.RedisManager
     **/
    @Bean
    public RedisManager redisManager() {
        return new RedisManager();
    }

    /**
     * 缓存管理器
     * @return org.crazycake.shiro.RedisCacheManager
     **/
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * thymeleaf中使用shiro标签
     * @return at.pollux.thymeleaf.shiro.dialect.ShiroDialect
     **/
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
