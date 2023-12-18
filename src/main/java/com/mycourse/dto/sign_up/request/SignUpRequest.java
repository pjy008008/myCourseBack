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
        @Schema(description = "이수 학기",example = "2")
        Integer completionsem,
        @Schema(description = "이수 과목", example = "[[], [], [], [], [], [], [], []]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff,
        @Schema(description = "교양 이수 학점", example = "30")
        Integer ge
) {
}
