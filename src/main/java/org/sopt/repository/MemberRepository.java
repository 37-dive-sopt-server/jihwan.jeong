package org.sopt.repository;

import org.sopt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
//    boolean isExistEmail(String email);
//    boolean isExistId(Long id);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
