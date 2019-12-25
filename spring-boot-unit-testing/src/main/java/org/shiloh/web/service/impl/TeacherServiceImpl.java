package org.shiloh.web.service.impl;

import org.shiloh.web.dao.TeacherDao;
import org.shiloh.web.entity.Teacher;
import org.shiloh.web.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:38
 * @Description TODO
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherDao.findById(id).orElse(null);
    }

    @Override
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherDao.deleteById(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.save(teacher);
    }
}
