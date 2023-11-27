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
@RequestMapping("/subject")  // Change the base path to "/subject"
public class SubjectController {
    private final SubjectService subjectService;

    @Operation(summary = "과목 목록 조회")
    @GetMapping  // Remove the "/main" path here, as it's already defined in the class-level annotation
    public ApiResponse getAllSubjects() {
        return ApiResponse.success(subjectService.getSubjects());
    }
}

