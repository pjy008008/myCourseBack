package com.mycourse.controller;

import com.mycourse.dto.ApiResponse;
import com.mycourse.security.AdminAuthorize;
import com.mycourse.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자용 API")   //Swagger(OpenAPI) 문서를 자동으로 생성하는 데 사용되는 어노테이션 -> API에 대한 그룹화 및 태그 지정이 가능
@RequiredArgsConstructor    //필드를 사용하는 생성자를 자동으로 생성 -> final로 선언된 필드에 대한 생성자를 편리하게 생성
@AdminAuthorize             //해당 API에 접근할 권한이 있는지 확인하는 사용자 정의 어노테이션
@RestController             //RESTful 웹 서비스의 컨트롤러 -> 각 메서드의 반환 값이 HTTP 응답 본문으로 직접 전송
@RequestMapping("/admin")   //모든 메서드에 대한 기본적인 요청 매핑을 설정
public class AdminController {
    private final AdminService adminService;


    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    public ApiResponse getAllAdmins() {
        return ApiResponse.success(adminService.getAdmins());
    }
}
