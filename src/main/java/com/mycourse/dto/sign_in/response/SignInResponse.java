package com.mycourse.dto.sign_in.response;

import com.mycourse.common.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
        @Schema(description = "회원 이름", example = "박준유")
        String name,
        @Schema(description = "회원 유형", example = "USER")
        MemberType type,
        String token
) {
}
