package org.shiloh.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.shiloh.app.entity.OnlineUserInfo;
import org.shiloh.app.entity.User;
import org.shiloh.app.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 15:38
 * @description
 */
@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public List<OnlineUserInfo> getOnlineUserInfoList() {
        List<OnlineUserInfo> onlineUserInfoList = new ArrayList<>();
        // 获取所有有效的Session，通过该Session，可以获取到当前用户的principal(主要)信息
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        OnlineUserInfo onlineUserInfo;
        User user;
        SimplePrincipalCollection principalCollection;
        for (Session session : sessions) {
            onlineUserInfo = new OnlineUserInfo();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                // 通过session key获取到用户的主要信息集合
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                // 获取user
                user = (User) principalCollection.getPrimaryPrincipal();
                // 设置在线用户的基本信息
                onlineUserInfo.setUserId(user.getId().toString());
                onlineUserInfo.setUsername(user.getUsername());
            }
            // 设置在线用户的主要信息
            onlineUserInfo.setId(session.getId().toString());
            onlineUserInfo.setHost(session.getHost());
            onlineUserInfo.setStartTimestamp(session.getStartTimestamp());
            onlineUserInfo.setLastAccessTime(session.getLastAccessTime());
            if (session.getTimeout() == 0) {
                onlineUserInfo.setStatus("离线");
            } else {
                onlineUserInfo.setStatus("在线");
            }
            onlineUserInfo.setTimeout(session.getTimeout());
            onlineUserInfoList.add(onlineUserInfo);
        }
        return onlineUserInfoList;
    }

    /**
     * 强制踢出某个用户
     * 用户在登录时选择了记住我的话则无法踢出
     *
     * @param sessionId 会话id
     * @return java.lang.Boolean
     * @author lxlei
     **/
    @Override
    public Boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
