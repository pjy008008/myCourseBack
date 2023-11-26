package com.mycourse.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component //AuthenticationEntryPoint는 인증이 실패했을 때 처리해야 할 동작을 정의
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;   //Spring MVC에서 예외를 처리하고 적절한 응답을 생성하는 인터페이스 -> 인증 실패 시 적절한 응답을 생성
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        resolver.resolveException(request, response, null, (Exception) request.getAttribute("exception"));  //실패한 인증 시 호출
    }

}
