package org.shiloh.web.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Shiloh
 * @Date 2019/11/28 11:18
 * @Description TODO
 */
@Entity
@Table(name = "tb_user_log")
@org.hibernate.annotations.Table(appliesTo = "tb_user_log", comment = "用户操作日志记录表")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UserLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", columnDefinition = " varchar(50) comment '用户名' ")
    private String username;

    @Column(name = "operation", columnDefinition = " varchar(50) comment '操作' ")
    private String operation;

    @Column(name = "response_time", columnDefinition = " bigint(20) comment '响应毫秒数' ")
    private Long responseTime;

    @Column(name = "request_method", columnDefinition = " varchar(255) comment '请求方法' ")
    private String requestMethod;

    @Column(name = "request_params", columnDefinition = " varchar(500) comment '请求参数' ")
    private String requestParams;

    @Column(name = "ip_address", columnDefinition = " varchar(50) comment 'ip地址' ")
    private String ipAddress;

    @CreatedDate
    @Column(name = "create_time", columnDefinition = " datetime comment '创建时间' ")
    private Date createTime;
}
