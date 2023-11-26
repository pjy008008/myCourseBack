package com.mycourse.handler;

import com.mycourse.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice   //전역적으로 예외를 처리하는 핸들러 클래스
public class ExceptionResponseHandler {
    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})   //클라이언트의 잘못된 요청이나 찾을 수 없는 자원에 대한 요청 등에서 발생하는 일반적인 예외를 처리
    public ResponseEntity<ApiResponse> handleCommonException(Exception e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)  //보안 권한이 없는 경우 발생하는 예외를 처리
    public ResponseEntity<ApiResponse> handleAccessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("접근이 거부되었습니다."));
    }

    @ExceptionHandler(Exception.class)  //예상치 못한 예외가 발생했을 때 처리
    public ResponseEntity<ApiResponse> handleUnexpectedException() {
        return ResponseEntity.internalServerError().body(ApiResponse.error("서버에 문제가 발생했습니다."));
    }

    @ExceptionHandler(SignatureException.class) //
    public ResponseEntity<ApiResponse> handleSignatureException() { //WT(JSON Web Token)와 관련된 예외를 처
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("토큰이 유효하지 않습니다."));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse> handleMalformedJwtException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("올바르지 않은 토큰입니다."));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse> handleExpiredJwtException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("토큰이 만료되었습니다. 다시 로그인해주세요."));
    }
}
