package com.mycourse.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) //적용 위치 지정
@Retention(RetentionPolicy.RUNTIME)     //유지되는 기간 지정-> 실행시간까지 유지
@PreAuthorize("hasAuthority('USER')")   //실제 권한 검사-> USER 권한 있는지
public @interface UserAuthorize {
}
