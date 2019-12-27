package org.shiloh.app.service;

import org.shiloh.app.entity.OnlineUserInfo;

import java.util.List;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 15:36
 * @description 对在线用户进行操作的service
 */
public interface SessionService {
    /**
     * 查看所有在线用户信息列表
     * @return java.util.List<org.shiloh.app.entity.OnlineUserInfo>
     **/
    List<OnlineUserInfo> getOnlineUserInfoList();

    /**
     * 强制某个用户下线
     * @param sessionId 会话id
     * @return java.lang.Boolean
     **/
    Boolean forceLogout(String sessionId);
}
