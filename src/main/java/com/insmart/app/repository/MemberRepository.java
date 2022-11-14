package com.insmart.app.repository;

import com.insmart.app.bean.ResponseMember;
import com.insmart.app.model.Member;
import com.insmart.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT * FROM Members m WHERE m.username = ?1 LIMIT 1",nativeQuery = true)
    Member findByUsername(String username);
    @Query(value = "SELECT * FROM Members m WHERE m.member_id IN(?1)",nativeQuery = true)
    List<Member> findMemberByIds(List<Long> ids);
}

