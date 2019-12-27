package org.shiloh.app.dao;

import org.shiloh.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 17:01
 * @description
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据username查询
     * @param username 用户名
     * @return org.shiloh.app.entity.User
     **/
    User findByUsername(String username);
}
