package com.insmart.app.repository;

import com.insmart.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    User findByUsernameAndPassword(String username, String password);
    @Query(value = "SELECT * FROM Users u WHERE u.username like %?1% OR u.email like %?1% OR u.code like %?1%",nativeQuery = true)
    List<User> findByUsernameOrEmailOrName(String input);
}