package com.insmart.app.repository;

import com.insmart.app.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT p FROM Permission p WHERE p.user.id = (SELECT u.id FROM User u WHERE u.username = ?1)")
    List<Permission> findByUsername(String username);
}
