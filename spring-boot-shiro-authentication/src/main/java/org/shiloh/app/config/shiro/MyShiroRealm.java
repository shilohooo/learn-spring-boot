package org.shiloh.app.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.shiloh.app.dao.UserDao;
import org.shiloh.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 15:54
 * @description
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 用户角色权限认证
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 用户登录身份认证
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的username和password
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println(String.format("用户：%1$s进行认证---MyShiroRealm#doGetAuthenticationInfo", username));

        // 通过用户名查询数据库中对应的用户信息
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (user.getStatus().equals(0)) {
            throw new LockedAccountException("该帐号已被锁定，请联系管理员！");
        }
        // 通过认证后返回一个带有user信息的结果
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
