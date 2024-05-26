package org.shiloh.app.common.service;

import org.shiloh.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lxlei
 * @date Created in 2020/1/16 9:31
 * @description 自定义用户登录认证
 */
@Configuration
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 用户登录认证：用户名可随意填写，密码为123456
     * 登录成功后赋予用户user:add权限
     * @author lxlei
     * @param username 登录用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     * @date 2020/1/16 9:43
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User()
                .setUsername(username)
                .setPassword(this.passwordEncoder.encode("123456"));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
