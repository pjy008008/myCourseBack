package com.mycourse.dto.sign_up.response;

import com.mycourse.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

public record SignUpResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        UUID id,
        @Schema(description = "회원 아이디", example = "cbnu2020039028")
        String account,
        @Schema(description = "선호분야", example = "소프트웨어")
        String prefer,
        @Schema(description = "학번", example = "20")
        Integer stdnum,
        @Schema(description = "이수 학기",example = "6")
        Integer completionsem,
        @Schema(description = "이수 과목", example = "[[], [], [], [], [], [], [], []]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff
) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getAccount(),
                member.getPrefer(),
                member.getStdnum(),
                member.getCompletionsem(),
                member.getSubject(),
                member.getOnoff()
        );
    }
}
