package org.shiloh.app.dao;

import org.shiloh.app.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author shiloh
 * @Date Created in 2019/12/27 12:07
 * @description
 */
@Repository
public interface PermissionDao extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
}
