package com.mycourse.repository;

import com.mycourse.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> { //Member는 엔티티, UUID는 엔티티의 식별자
    List<Subject> findAll(); //계정 정보 기반 db에서 회원 찾는 메서드
}
