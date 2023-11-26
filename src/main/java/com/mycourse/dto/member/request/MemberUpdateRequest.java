package com.mycourse.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MemberUpdateRequest(
        @Schema(description = "회원 비밀번호", example = "1234")
        String password,
        @Schema(description = "회원 새 비밀번호", example = "1234")
        String newPassword,
        @Schema(description = "선호 분야", example = "ai")
        String prefer,
        @Schema(description = "학번", example = "20")
        Integer stdnum,
        @Schema(description = "이수 학기",example = "6")
        Integer grade,
        @Schema(description = "이수 과목", example = "[[123, 124], [125, 126]]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff
) {
}
