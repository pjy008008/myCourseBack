package com.mycourse.controller;

import com.mycourse.dto.ApiResponse;
import com.mycourse.dto.sign_in.request.SignInRequest;
import com.mycourse.dto.sign_up.request.SignUpRequest;
import com.mycourse.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 가입 및 로그인")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class SignController {
    private final SignService signService;

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {     //JSON 데이터를 SignUpRequest 객체로 변환
        return ApiResponse.success(signService.registMember(request));  //이 객체를 이용하여 함수를 호출하여 회원 가입을 수행한 후, 성공적인 응답으로 감싸서 반환
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(signService.signIn(request));
    }
}