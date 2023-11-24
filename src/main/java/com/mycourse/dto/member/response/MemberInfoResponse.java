package com.mycourse.dto.member.response;

import com.mycourse.common.MemberType;
import com.mycourse.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfoResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        UUID id,
        @Schema(description = "회원 아이디", example = "cbnu2020039028")
        String account,
        @Schema(description = "전공", example = "소프트웨어")
        String major,
        @Schema(description = "회원 나이", example = "23")
        Integer age,
        @Schema(description = "학년",example = "2")
        Integer grade,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "회원 생성일", example = "2023-22-22T15:00:00")
        LocalDateTime createdAt,
        @Schema(description = "이수 과목",example = "[123,124]")
        String subject
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getAccount(),
                member.getMajor(),
                member.getStdnum(),
                member.getGrade(),
                member.getType(),
                member.getCreatedAt(),
                member.getSubject()
        );
    }
}
