package com.insmart.app.repository;
import com.insmart.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
@Query(value = "SELECT * FROM Users u WHERE u.username like %?1% or u.email like %?1% or u.phone_number like %?1%", nativeQuery = true)
List<User> findByUsernameOrEmail(String username);
}
