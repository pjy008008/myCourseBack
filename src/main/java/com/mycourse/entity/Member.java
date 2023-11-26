package com.mycourse.entity;

import com.mycourse.common.MemberType;
import com.mycourse.dto.member.request.MemberUpdateRequest;
import com.mycourse.dto.sign_up.request.SignUpRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 생성자를 생성하되, 접근 제한자를 protected로 설정
@Getter
@Entity
public class Member {
    @Column(nullable = false, scale = 20, unique = true) //고유해야 함
    private String account;
    @Column(nullable = false)
    private String password;
    private String major;
    private Integer stdnum;
    private Integer grade;
    private List<List<Integer>> subject;
    @Enumerated(EnumType.STRING)    //열거형 타입의 필드를 문자열로 매핑하도록 지정
    private MemberType type;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Id //해당 필드가 기본 키임
    @GeneratedValue(strategy = GenerationType.UUID) //기본 키 값을 UUID를 자자동 생성
    private UUID id;

    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .account(request.account())
                .password(encoder.encode(request.password()))
                .major(request.major())
                .stdnum(request.stdnum())
                .grade(request.grade())
                .subject(request.subject())
                .type(MemberType.USER)
                .build();
    }

    @Builder
    private Member(String account, String password, String major, Integer stdnum, Integer grade,List subject, MemberType type) {
        this.account = account;
        this.password = password;
        this.major = major;
        this.stdnum = stdnum;
        this.grade = grade;
        this.subject = subject;
        this.type = type;

    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank()
                ? this.password : encoder.encode(newMember.newPassword());
        this.major = newMember.major();
        this.stdnum = newMember.stdnum();
        this.grade = newMember.grade();
        this.subject = newMember.subject();
    }
}
