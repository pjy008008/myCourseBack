package com.mycourse.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Order(0)
@RequiredArgsConstructor
@Component  //각 요청당 한 번만 실행되도록 보장하며, JWT (JSON Web Token)를 사용한 인증을 처리
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            //HTTP요청 헤더에서 Authorization" 헤더 읽어들임
            // ->만약 헤더가 "Bearer "로 시작하면, 해당 부분을 제거 실제 JWT 토큰만 추출

            User user = parseUserSpecification(token);
            // JWT 토큰을 분석하고 사용자 정보를 추출
            //tokenProvider::validateTokenAndGetSubject를 호출하여 토큰을 유효성 검증하고, 토큰에 포함된 사용자 정보를 문자열로 얻음
            //얻은 문자열을 "anonymous:anonymous" 형태로 분할하여 사용자 이름과 권한 정보를 추출
            //User 객체를 생성하여 해당 정보를 담음

            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
            //사용하여 인증된 사용자 객체를 생성
            //생성된 인증 객체에는 사용자 정보, 토큰, 권한 정보가 포함

            authenticated.setDetails(new WebAuthenticationDetails(request));
            //사용자의 IP 주소와 세부 정보를 설정

            SecurityContextHolder.getContext().setAuthentication(authenticated);
            //사용자의 IP 주소와 세부 정보를 설정
        } catch (Exception e) {
            request.setAttribute("exception", e);
            //예외를 request의 속성에 저장
        }

        filterChain.doFilter(request, response);
        //모든 작업이 완료되면 filterChain.doFilter(request, response)를 호출하여 나머지 필터 체인에 요청을 전달
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private User parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse("anonymous:anonymous")
                .split(":");

        return new User(split[0], "", List.of(new SimpleGrantedAuthority(split[1])));
    }
}
