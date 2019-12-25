package org.shiloh.web.dao;

import org.shiloh.web.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Shiloh
 * @Date 2019/11/28 15:44
 * @Description TODO
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
}
