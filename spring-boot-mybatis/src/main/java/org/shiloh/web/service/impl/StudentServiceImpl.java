package org.shiloh.web.service.impl;

import org.shiloh.web.entity.Student;
import org.shiloh.web.mapper.StudentMapper;
import org.shiloh.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/28 9:36
 * @Description TODO
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentMapper.deleteStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }
}
