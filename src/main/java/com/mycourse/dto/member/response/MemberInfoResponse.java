package com.mycourse.dto.member.response;

import com.mycourse.common.MemberType;
import com.mycourse.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MemberInfoResponse(
        @Schema(description = "선호 과목", example = "ai")
        String prefer,
        @Schema(description = "학번", example = "20")
        Integer stdnum,
        @Schema(description = "학년",example = "2")
        Integer grade,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "이수 과목", example = "[[123, 124], [125, 126]]")
        List<List<Integer>> subject,
        @Schema(description = "공개 여부", example = "true")
        Boolean onoff

) {
    public static MemberInfoResponse from(Member member) {  //Member 엔티티 객체에서 MemberInfoResponse 객체로 변환
        return new MemberInfoResponse(
                member.getPrefer(),
                member.getStdnum(),
                member.getGrade(),
                member.getType(),
                member.getSubject(),
                member.getOnoff()
        );
    }
}
