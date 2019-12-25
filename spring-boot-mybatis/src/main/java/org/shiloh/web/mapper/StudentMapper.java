package org.shiloh.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.shiloh.web.entity.Student;

import java.util.List;

/**
 * @Author Shiloh
 * @Date 2019/11/27 18:04
 * @Description TODO
 */
@Mapper
//@Component
public interface StudentMapper {
    List<Student> findAll();

    Student findById(@Param("id") Long id);

    void addStudent(Student student);

    void deleteStudent(@Param("id") Long id);

    void updateStudent(Student student);
}
