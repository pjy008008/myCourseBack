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
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Column(nullable = false, scale = 20, unique = true)
    private String account;
    @Column(nullable = false)
    private String password;
    private String major;
    private Integer stdnum;
    private Integer grade;
    private String subject;
    @Enumerated(EnumType.STRING)
    private MemberType type;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private Member(String account, String password, String major, Integer stdnum, Integer grade,String subject, MemberType type) {
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
