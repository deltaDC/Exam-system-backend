package com.deltadc.examsystem.ExamResult;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Exam.ExamRepository;
import com.deltadc.examsystem.User.User;
import com.deltadc.examsystem.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final UserRepository userRepository;
    private final ExamRepository examRepository;

    public ResponseEntity<?> createExamResult(ExamResult examResult) {
        Long userId = examResult.getUserId();
        Long examId = examResult.getExamId();

        User user = userRepository.findById(userId).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();
        List<ExamResult> examResultHistory = examResultRepository.findByUserIdAndExamId(userId, examId);
        if(examResultHistory.size() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nguoi dung da nop bai thi nay truoc do");
        }

        ExamResult newResult = new ExamResult(
                examResult.getUserId(),
                examResult.getExamId(),
                examResult.getScore(),
                examResult.getStatus()
        );

        examResultRepository.save(newResult);

        return ResponseEntity.ok(newResult);
    }

    public ResponseEntity<?> getAllExamResults() {
        List<ExamResult> examResultList = examResultRepository.findAll();

        return ResponseEntity.ok(examResultList);
    }

    public ResponseEntity<?> getExamResultsById(Long examResultId) {
        Optional<ExamResult> examResultList = examResultRepository.findById(examResultId);

        return ResponseEntity.ok(examResultList);
    }

    public ResponseEntity<?> getExamResultByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        List<ExamResult> examResultList = examResultRepository.findByUserId(userId);

        return ResponseEntity.ok(examResultList);
    }

    public ResponseEntity<?> getExamResultByExamId(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow();

        List<ExamResult> examResultList = examResultRepository.findByExamId(examId);

        return ResponseEntity.ok(examResultList);

    }

    public ResponseEntity<?> getExamResultsByUserIdExamId(Long userId, Long examId) {
        User user = userRepository.findById(userId).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();

        List<ExamResult> examResultList = examResultRepository.findByUserIdAndExamId(userId, examId);

        return ResponseEntity.ok(examResultList);
    }

    public ResponseEntity<?> deleteExamResultById(Long examResultId) {
        ExamResult examResult = examResultRepository.findById(examResultId).orElseThrow();

        examResultRepository.delete(examResult);
        return ResponseEntity.status(HttpStatus.OK)
                .body("da xoa examreuslt theo id");
    }

    public ResponseEntity<?> editExamResultById(Long examResultId, ExamResult newExamResult) {
        ExamResult examResult = examResultRepository.findById(examResultId).orElseThrow();

        if(newExamResult.getScore() > 0) {
            examResult.setScore(newExamResult.getScore());
        }
        if(newExamResult.getStatus() != null) {
            examResult.setStatus(newExamResult.getStatus());
        }

        examResultRepository.save(examResult);

        return ResponseEntity.ok(examResult);
    }

    public ResponseEntity<?> getExamAverageScore(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow();

        List<ExamResult> examResultList = examResultRepository.findByExamId(examId);
        int totalScore = 0;
        for(ExamResult e : examResultList) {
            totalScore += e.getScore();
        }
        double average = (double) totalScore / examResultList.size();

        return ResponseEntity.ok(average);
    }

    public ResponseEntity<?> getUserResults(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Long userId = user.getUserId();

        List<ExamResult> userResults = examResultRepository.findByUserId(userId);

        return ResponseEntity.ok(userResults);
    }
}
