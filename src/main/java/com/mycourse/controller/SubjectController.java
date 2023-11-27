package com.mycourse.controller;

import com.mycourse.dto.ApiResponse;
import com.mycourse.security.UserAuthorize;
import com.mycourse.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인 후 사용할 수 있는 API")
@RequiredArgsConstructor
@UserAuthorize
@RestController
@RequestMapping("/main")
public class SubjectController {
    private final SubjectService subjectService;

    @Operation(summary = "과목 목록 조회")    //Swagger(OpenAPI) 문서를 자동으로 생성하기 위한 어노테이션 -> 메서드에 대한 설명(summary)을 지정
    @GetMapping("/main")
    public ApiResponse getAllSubjects() {
        return ApiResponse.success(subjectService.getSubjects());
    } //회원 목록을 가져온 후, 성공적인 응답으로 감싸서 반환
}
