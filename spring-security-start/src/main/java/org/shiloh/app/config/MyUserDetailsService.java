package org.shiloh.app.config;

import org.apache.commons.lang3.StringUtils;
import org.shiloh.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 22:00
 * @description 自定义user认证过程
 */
@Configuration
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("123456"));
        // 输出加密后的密码
        System.out.println(String.format("用户加密之后的密码为：%1$s", user.getPassword()));
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (StringUtils.equalsIgnoreCase("root", username)) {
            // 如果是root用户登录才赋予admin权限
            grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        } else {
            // 其余帐号赋予普通用户的权限
            grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("user");
        }
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                grantedAuthorityList);
    }
}
