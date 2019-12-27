package org.shiloh.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 15:32
 * @description 在线用户的基本信息
 */
@Data
public class OnlineUserInfo implements Serializable {
    private static final long serialVersionUID = -6536291987064339669L;

    /**
     * session id
     */
    private String id;

    /**
     * user id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户主机地址
     */
    private String host;

    /**
     * 用户登录时的系统IP地址
     */
    private String systemHost;

    /**
     * 状态
     */
    private String status;

    /**
     * 会话创建时间
     */
    private Date startTimestamp;

    /**
     * 会话最后一次访问时间
     */
    private Date lastAccessTime;

    /**
     * 超时时间
     */
    private Long timeout;
}
