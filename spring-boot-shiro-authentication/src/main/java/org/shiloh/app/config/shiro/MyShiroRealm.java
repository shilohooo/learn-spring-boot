package org.shiloh.app.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.shiloh.app.dao.UserDao;
import org.shiloh.app.entity.Permission;
import org.shiloh.app.entity.Role;
import org.shiloh.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 15:54
 * @description
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 登录用户角色权限认证
     *
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        System.out.println(String.format("用户：%1$s进行权限认证----MyShiroRealm#doGetAuthorizationInfo", username));
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户的角色集合
        User userInDb = userDao.findByUsername(username);
        Set<Role> roles = userInDb.getRoles();
        Set<String> roleNameSet = new HashSet<>();
        roles.forEach(role -> roleNameSet.add(role.getName()));
        // 将当前登录用户的角色信息保存到shiro中
        simpleAuthorizationInfo.setRoles(roleNameSet);

        // 获取用户权限集合
        Set<Permission> permissions = null;
        Set<String> permissionNameSet = new HashSet<>();
        for (Role role : roles) {
            permissions = role.getPermissions();
            for (Permission permission : permissions) {
                permissionNameSet.add(permission.getName());
            }
        }
        // 将当前登录用户的角色对应的权限信息保存到shiro中
        simpleAuthorizationInfo.setStringPermissions(permissionNameSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录身份认证
     *
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的username和password
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println(String.format("用户：%1$s进行登录认证---MyShiroRealm#doGetAuthenticationInfo", username));

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
