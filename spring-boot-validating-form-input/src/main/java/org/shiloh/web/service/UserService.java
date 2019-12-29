package org.shiloh.web.service;

import org.shiloh.web.entity.User;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/26 17:34
 * @Description TODO
 */
public interface UserService {
    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> list();

    /**
     * 根据id获取单个用户
     *
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * 新增用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 修改用户
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id删除用户
     *
     * @param user
     */
    void update(User user);
}
