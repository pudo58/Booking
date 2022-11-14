package com.insmart.app.repository;

import com.insmart.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("SELECT t FROM Token t WHERE t.user.id = ?1")
    Token findByUserId(Long id);
    @Query("SELECT t FROM Token t WHERE t.code = ?1")
    Token findByCode(String code);
}
