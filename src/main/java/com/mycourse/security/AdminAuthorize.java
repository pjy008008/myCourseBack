package com.mycourse.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) //대상 지정
@Retention(RetentionPolicy.RUNTIME)             //정보 유지 시점 저장
@PreAuthorize("hasAuthority('ADMIN')")          //메소드 실행 전에 주어진 표현식을 기반으로 권한 검사 수행, ADMIN 권한 가진 사용자만 접근 가능
public @interface AdminAuthorize {              //@interfacd : 커스텀 어노테이션을 정의
}
