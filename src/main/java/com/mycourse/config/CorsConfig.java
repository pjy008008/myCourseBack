package com.mycourse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //애플리케이션의 설정을 정의하는 클래스
@EnableWebMvc   //Spring MVC를 활성화
public class CorsConfig implements WebMvcConfigurer {       //CORS:다른 도메인에서의 자원 요청을 허용하도록 서버를 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                //모든 경로에 대한 CORS 설정 추가
                .allowedOrigins("http://localhost:3000")    //허용 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE") //정보 요청, 서버에 데이터 제출, 특정 리소스 업데이트 요청, 특정 리소스 삭제
                .allowCredentials(true);                    //자격증명(쿠키 및 HTTP 인증과 같은 인증 정보) 허용
    }
}