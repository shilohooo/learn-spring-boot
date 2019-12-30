package org.shiloh.app.config.security;

import org.shiloh.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 17:33
 * @description 自定义处理用户登录认证的逻辑
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        // 设置帐号和加密过后的密码
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                // 赋予登录成功的用户admin权限
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
