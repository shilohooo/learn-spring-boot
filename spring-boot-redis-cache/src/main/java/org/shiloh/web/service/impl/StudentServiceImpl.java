package org.shiloh.web.service.impl;

import org.shiloh.web.dao.StudentDao;
import org.shiloh.web.entity.Student;
import org.shiloh.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Shiloh
 * @Date 2019/11/28 15:45
 * @Description TODO
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student get(Long id) {
        return studentDao.findById(id).orElse(null);
    }
}
