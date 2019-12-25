package org.shiloh.web.service;

import org.shiloh.web.entity.Student;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/28 9:36
 * @Description TODO
 */
public interface StudentService {
    List<Student> findAll();

    Student findById(Long id);

    void addStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Student student);
}
