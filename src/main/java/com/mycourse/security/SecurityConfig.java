package com.mycourse.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPoint entryPoint;

    private static final String[] ALLOWED_URIS = {"/sign-up", "/sign-in"};  //모든 사용자에게 허용되는 URL 목록

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()   // CSRF 보호 비활성화
                .headers(headers -> headers.frameOptions().sameOrigin())    //X-Frame-Options를 설정-> 같은 출처에서만 프레임을 허용
                .authorizeHttpRequests(request ->   //요청에 대한 권한 부여 설정
                        request.requestMatchers(ALLOWED_URIS).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> //세션 관리를 설정 -> 세션을 생성하지 않도록 지정
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint)) //예외 처리 설정 -> 인증 예외 처리
                .build();
    }

    @Bean   //비밀번호 인코딩
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
