package com.mycourse.dto.member.response;

import com.mycourse.common.MemberType;
import com.mycourse.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MemberInfoResponse(
        @Schema(description = "회원 아이디", example = "cbnu2020039028")
        String account,
        @Schema(description = "선호 과목", example = "ai")
        String prefer,
        @Schema(description = "학번", example = "20")
        Integer stdnum,
        @Schema(description = "이수 학기",example = "6")
        Integer completionsem,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "이수 과목", example = "[[123, 124], [125, 126]]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff,
        @Schema(description = "교양 이수 학점", example = "30")
        Integer ge

) {
    public static MemberInfoResponse from(Member member) {  //Member 엔티티 객체에서 MemberInfoResponse 객체로 변환
        return new MemberInfoResponse(
                member.getAccount(),
                member.getPrefer(),
                member.getStdnum(),
                member.getCompletionsem(),
                member.getType(),
                member.getSubjectAsList(),
                member.getOnoff(),
                member.getGe()
        );
    }
}
