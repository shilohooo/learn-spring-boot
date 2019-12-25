package org.shiloh.web.service;

import org.shiloh.web.entity.Teacher;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:37
 * @Description TODO
 */
public interface TeacherService {
    List<Teacher> findAll();

    Teacher findById(Long id);

    void save(Teacher teacher);

    void delete(Long id);

    void update(Teacher teacher);
}
