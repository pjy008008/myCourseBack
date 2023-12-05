package com.mycourse.service;

import com.mycourse.common.MemberType;
import com.mycourse.repository.MemberRepository;
import com.mycourse.dto.member.request.MemberUpdateRequest;
import com.mycourse.dto.member.response.MemberDeleteResponse;
import com.mycourse.dto.member.response.MemberInfoResponse;
import com.mycourse.dto.member.response.MemberUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(UUID id) {
        return memberRepository.findById(id)
                .map(MemberInfoResponse::from)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public MemberDeleteResponse deleteMember(UUID id) {
        if (!memberRepository.existsById(id)) return new MemberDeleteResponse(false);
        memberRepository.deleteById(id);
        return new MemberDeleteResponse(true);
    }

    @Transactional
    public MemberUpdateResponse updateMember(UUID id, MemberUpdateRequest request) {
        return memberRepository.findById(id)
                .map(member -> {
                    // 입력한 비밀번호와 새로운 비밀번호 모두 null이거나
                    // 입력한 비밀번호가 null이거나 원래 비밀번호와 일치하면 업데이트 수행
                    if ((request.password() == null && request.newPassword() == null) ||
                            (request.password() == null || encoder.matches(request.password(), member.getPassword()))) {
                        if(request.newPassword() != null){
                            member.setPassword(request, encoder);
                        }

                        if (request.prefer() != null) {
                            member.setPrefer(request.prefer());
                        }
                        if (request.stdnum() != null) {
                            member.setStdnum(request.stdnum());
                        }
                        if (request.completionsem() != null) {
                            member.setCompletionsem(request.completionsem());
                        }
                        if (request.onoff() != null) {
                            member.setOnoff(request.onoff());
                        }
                        if (request.subject() != null) {
                            member.setSubjectFromList(request.subject());
                        }
                        return MemberUpdateResponse.of(true, member);
                    }
                    // 입력한 비밀번호가 null이 아니고 원래 비밀번호와 일치하지 않으면 예외 발생
                    throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
                })
                .orElseThrow(() -> new IllegalArgumentException("해당 ID에 해당하는 회원을 찾을 수 없습니다."));
    }


    @Transactional(readOnly = true)
    public List<MemberInfoResponse> getMembers() {
        return memberRepository.findAllByType(MemberType.USER).stream()
                .map(MemberInfoResponse::from)  //엔티티->DTO 변환 후
                .toList();  //리스트로 반환
    }
}
