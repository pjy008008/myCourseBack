package com.mycourse.dto.sign_up.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SignUpRequest(
        @Schema(description = "회원 아이디", example = "cbnu2020039028")
        String account,
        @Schema(description = "회원 비밀번호", example = "123")
        String password,
        @Schema(description = "선호 분야", example = "ai")
        String prefer,
        @Schema(description = "학번",example = "20")
        Integer stdnum,
        @Schema(description = "학년",example = "2")
        Integer grade,
        @Schema(description = "이수 과목", example = "[[123, 124], [125, 126]]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff
) {
}
