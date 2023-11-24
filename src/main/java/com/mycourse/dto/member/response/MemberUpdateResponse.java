package com.mycourse.dto.member.response;

import com.mycourse.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record MemberUpdateResponse(
        @Schema(description = "회원 정보 수정 성공 여부", example = "true")
        boolean result,
        @Schema(description = "전공", example = "소프트웨어")
        String major,
        @Schema(description = "학번", example = "20")
        Integer stdnum,
        @Schema(description = "학년",example = "2")
        Integer grade,
        @Schema(description = "이수 과목",example = "[123,124]")
        String subject
) {
    public static MemberUpdateResponse of(boolean result, Member member) {
        return new MemberUpdateResponse(result, member.getMajor(), member.getStdnum(),member.getGrade(),member.getSubject());
    }
}
