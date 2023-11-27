package com.mycourse.service;

import com.mycourse.entity.Subject;
import com.mycourse.dto.subject.response.SubjectResponse;
import com.mycourse.entity.Subject;
import com.mycourse.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional(readOnly = true)
    public List<SubjectResponse> getSubjects() {
        try {
            List<Subject> subjects = subjectRepository.findAll();
            return subjects.stream()
                    .map(SubjectResponse::from)
                    .toList();
        } catch (Exception e) {
            // 디버깅 목적으로 예외를 기록합니다.
            e.printStackTrace();
            throw e; // 예외를 전파하여 상위로 전달합니다.
        }
    }
}

