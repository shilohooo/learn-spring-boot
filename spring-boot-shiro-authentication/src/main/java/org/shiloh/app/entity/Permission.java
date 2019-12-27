package org.shiloh.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 11:42
 * @description 权限实体
 */
@Entity
@Table(name = "tb_permission")
@Setter
@Getter
public class Permission implements Serializable {

    private static final long serialVersionUID = -386615110165504189L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "name", length = 100)
    private String name;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id", columnDefinition = "bigint(20)")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", columnDefinition = "bigint(20)")})
    private Set<Role> roles = new HashSet<>();
}
