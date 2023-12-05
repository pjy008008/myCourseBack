package com.mycourse.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mycourse.common.MemberType;
import com.mycourse.dto.member.request.MemberUpdateRequest;
import com.mycourse.dto.sign_up.request.SignUpRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Column(nullable = false, scale = 20, unique = true)
    private String account;
    @Column(nullable = false)
    private String password;
    private String prefer;
    private Integer stdnum;
    private Integer completionsem;
    private Boolean onoff;
    private String subject;
    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .account(request.account())
                .password(encoder.encode(request.password()))
                .prefer(request.prefer())
                .stdnum(request.stdnum())
                .completionsem(request.completionsem())
                .subject(request.subject())
                .onoff(request.onoff())
                .type(MemberType.USER)
                .build();
    }

    @Builder
    private Member(String account, String password, String prefer, Integer stdnum, Integer completionsem, List subject, MemberType type, Boolean onoff) {
        this.account = account;
        this.password = password;
        this.prefer = prefer;
        this.stdnum = stdnum;
        this.completionsem = completionsem;
        this.setSubjectFromList(subject);
        this.type = type;
        this.onoff = onoff;
    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = (newMember.newPassword() == null || newMember.newPassword().isBlank())
                ? this.password : encoder.encode(newMember.newPassword());
        this.prefer = newMember.prefer();
        this.stdnum = newMember.stdnum();
        this.completionsem = newMember.completionsem();
        this.setSubjectFromList(newMember.subject());
        this.onoff = newMember.onoff();
    }

    // 문자열을 List of List로 변환하는 메서드
    public List<List<Integer>> getSubjectAsList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(subject, new TypeReference<List<List<Integer>>>(){});
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = (newMember.newPassword() == null || newMember.newPassword().isBlank())
                ? this.password : encoder.encode(newMember.newPassword());
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }

    public void setStdnum(Integer stdnum) {
        this.stdnum = stdnum;
    }

    public void setCompletionsem(Integer completionsem) {
        this.completionsem = completionsem;
    }

    public void setOnoff(Boolean onoff) {
        this.onoff = onoff;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    // List of List를 문자열로 변환하는 메서드
    public void setSubjectFromList(List<List<Integer>> subjectList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.subject = objectMapper.writeValueAsString(subjectList);
        } catch (JsonProcessingException e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
}
