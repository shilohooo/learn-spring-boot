package org.shiloh.web.service.impl;

import org.shiloh.web.dao.UserDao;
import org.shiloh.web.entity.User;
import org.shiloh.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/26 17:48
 * @Description TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public User get(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }
}
