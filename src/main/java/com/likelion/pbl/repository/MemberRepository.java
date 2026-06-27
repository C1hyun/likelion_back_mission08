package com.likelion.pbl.repository;

import com.likelion.pbl.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * [JpaRepository 기반 저장소]
 * JpaRepository<Member, Long>을 상속하면 아래 메서드가 자동 제공된다.
 *   save(), findById(), findAll(), deleteById(), existsById() 등
 *
 * findByName: 메서드 이름 규칙(findBy + 필드명)으로 쿼리 자동 생성
 *   → SELECT * FROM member WHERE name = ?
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    boolean existsByName(String name);
}
