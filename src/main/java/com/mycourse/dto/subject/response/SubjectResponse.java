package com.mycourse.dto.subject.response;

import com.mycourse.entity.Subject;
import io.swagger.v3.oas.annotations.media.Schema;


public record SubjectResponse(
        @Schema(description = "이수 학년", example = "1")
        Integer grade,
        @Schema(description = "이수 학기", example = "2")
        Integer sem,
        @Schema(description = "이수 구분", example = "1")
        String category,
        @Schema(description = "교과목번호", example = "5118003")
        Integer subnum,
        @Schema(description = "교과목명", example = "컴퓨터시스템개론")
        String subname,
        @Schema(description = "학점", example = "3")
        Integer score,
        @Schema(description = "ai", example = "1")
        Boolean ai,
        @Schema(description = "컴퓨터시스템", example = "1")
        Boolean cs,
        @Schema(description = "개발", example = "1")
        Boolean coding,
        @Schema(description = "교직이수", example = "1")
        Boolean teach
) {
    public static SubjectResponse from(Subject subject) {
        return new SubjectResponse(
                subject.getGrade(),
                subject.getSem(),
                subject.getCategory(),
                subject.getSubnum(),
                subject.getSubname(),
                subject.getScore(),
                subject.getAi(),
                subject.getCs(),
                subject.getCoding(),
                subject.getTeach()
        );
    }
}
