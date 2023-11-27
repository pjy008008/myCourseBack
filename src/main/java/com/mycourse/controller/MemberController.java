package com.mycourse.controller;

import com.mycourse.dto.ApiResponse;
import com.mycourse.dto.member.request.MemberUpdateRequest;
import com.mycourse.security.UserAuthorize;
import com.mycourse.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;      //범용 고유 식별자

@Tag(name = "로그인 후 사용할 수 있는 API")
@RequiredArgsConstructor
@UserAuthorize
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 정보 조회")
    @GetMapping
    public ApiResponse getMemberInfo(@AuthenticationPrincipal User user) {  //User 객체는 Spring Security에서 제공하는 사용자 정보 객체 -> 사용자의 식별자 가져옴
        return ApiResponse.success(memberService.getMemberInfo(UUID.fromString(user.getUsername())));   //문자열을 UUID로 변환하는 역할
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ApiResponse deleteMember(@AuthenticationPrincipal User user) {
        return ApiResponse.success(memberService.deleteMember(UUID.fromString(user.getUsername())));
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    public ApiResponse updateMember(@AuthenticationPrincipal User user, @RequestBody MemberUpdateRequest request) {
        return ApiResponse.success(memberService.updateMember(UUID.fromString(user.getUsername()), request));
    }


    @Operation(summary = "회원 목록 조회")    //Swagger(OpenAPI) 문서를 자동으로 생성하기 위한 어노테이션 -> 메서드에 대한 설명(summary)을 지정
    @GetMapping("/everyone")
    public ApiResponse getAllMembers() {
        return ApiResponse.success(memberService.getMembers());
    } //회원 목록을 가져온 후, 성공적인 응답으로 감싸서 반환
}
