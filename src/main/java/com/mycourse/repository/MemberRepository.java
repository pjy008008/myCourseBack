package com.mycourse.repository;

import com.mycourse.common.MemberType;
import com.mycourse.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> { //Member는 엔티티, UUID는 엔티티의 식별자
    Optional<Member> findByAccount(String account); //계정 정보 기반 db에서 회원 찾는 메서드
    List<Member> findAllByType(MemberType type);    //해당 타입의 모든 회원을 찾는 메서드
}
