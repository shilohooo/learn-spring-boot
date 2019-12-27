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
 * @Date Created in 2019/12/27 11:26
 * @description 角色实体
 */
@Entity
@Table(name = "tb_role")
@Setter
@Getter
public class Role implements Serializable {

    private static final long serialVersionUID = 6443118277430579719L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role",
            joinColumns = {@JoinColumn(name = "role_id", columnDefinition = "bigint(20)")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", columnDefinition = "bigint(20)")})
    private Set<User> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_role_permission",
            joinColumns = {@JoinColumn(name = "role_id", columnDefinition = "bigint(20)")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", columnDefinition = "bigint(20)")})
    private Set<Permission> permissions = new HashSet<>();
}
