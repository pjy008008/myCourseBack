package com.mycourse.dto.sign_up.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SignUpRequest(
        @Schema(description = "회원 아이디", example = "cbnu2020039028")
        String account,
        @Schema(description = "회원 비밀번호", example = "123")
        String password,
        @Schema(description = "전공", example = "소프트웨어")
        String major,
        @Schema(description = "학번",example = "20")
        Integer stdnum,
        @Schema(description = "학년",example = "2")
        Integer grade,
        @Schema(description = "이수 과목", example = "[[123, 124], [125, 126]]")
        List<List<Integer>> subject
) {
}
