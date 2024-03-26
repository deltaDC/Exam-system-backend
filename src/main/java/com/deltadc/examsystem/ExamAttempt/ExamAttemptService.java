package com.deltadc.examsystem.ExamAttempt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamAttemptService {

    private final ExamAttemptRepository examAttemptRepository;

    public ResponseEntity<?> createExamAttempt(ExamAttempt examAttempt) {
        ExamAttempt newExamAttempt = new ExamAttempt(
                examAttempt.getUserId(),
                examAttempt.getExamId(),
                examAttempt.getStartTime(),
                examAttempt.getEndTime()
        );

        examAttemptRepository.save(newExamAttempt);

        return ResponseEntity.ok(newExamAttempt);
    }

    public ResponseEntity<?> getExamAttemptById(Long examAttemptId) {
        ExamAttempt examAttempt = examAttemptRepository.findById(examAttemptId).orElseThrow();

        return ResponseEntity.ok(examAttempt);
    }

    public ResponseEntity<?> editExamAttemptById(Long examAttemptId, ExamAttempt newExamAttempt) {
        ExamAttempt examAttempt = examAttemptRepository.findById(examAttemptId).orElseThrow();

        if(newExamAttempt.getStartTime() != null) {
            examAttempt.setStartTime(newExamAttempt.getStartTime());
        }
        if(newExamAttempt.getEndTime() != null) {
            examAttempt.setEndTime(newExamAttempt.getEndTime());
        }

        examAttemptRepository.save(examAttempt);

        return ResponseEntity.ok(examAttempt);
    }

    public ResponseEntity<?> getExamAttemptByUserId(Long userId) {
        List<ExamAttempt> examAttemptList = examAttemptRepository.findByUserId(userId);

        return ResponseEntity.ok(examAttemptList);
    }

    public ResponseEntity<?> getExamAttemptByExamId(Long examId) {
        List<ExamAttempt> examAttemptList = examAttemptRepository.findByExamId(examId);

        return ResponseEntity.ok(examAttemptList);
    }
}
