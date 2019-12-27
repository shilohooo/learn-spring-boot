package org.shiloh.app.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
        // 开发登录接口，避免对用户信息查询2次
        filterChainDefinitionMap.put("/login", "anon");
        // druid数据源监控页面不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 配置退出登录的过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        // 除去以上配置的无需拦截的url，其余所有资源都必须通过认证之后才能访问，未通过认证则会自动访问loginUrl
        // 基于表单的拦截器；如/**=authc，如果没有登录会跳到相应的登录页面登录
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        // 配置securityManager，并注入shiroRealm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        // 配置自定义实现shiroRealm
        return new MyShiroRealm();
    }
}
