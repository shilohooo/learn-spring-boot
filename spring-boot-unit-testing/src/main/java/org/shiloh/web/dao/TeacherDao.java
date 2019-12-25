package org.shiloh.web.dao;

import org.shiloh.web.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Shiloh
 * @Date 2019/11/29 10:37
 * @Description TODO
 */
@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
}
