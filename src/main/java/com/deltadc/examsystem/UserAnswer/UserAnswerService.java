package com.deltadc.examsystem.UserAnswer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    public ResponseEntity<?> createUserAnswer(UserAnswer userAnswer) {
        UserAnswer newUserAnswer = new UserAnswer(
                userAnswer.getUserId(),
                userAnswer.getExamId(),
                userAnswer.getQuestionId(),
                userAnswer.getSelectedAnswer()
        );

        userAnswerRepository.save(newUserAnswer);

        return ResponseEntity.ok(newUserAnswer);
    }

    public ResponseEntity<?> getUserAnswerById(Long userAnswerId) {
        UserAnswer userAnswer = userAnswerRepository.findById(userAnswerId).orElseThrow();

        return ResponseEntity.ok(userAnswer);
    }

    public ResponseEntity<?> getUserAnswerByUserId(Long userId) {
        List<UserAnswer> userAnswer = userAnswerRepository.findByUserId(userId);

        return ResponseEntity.ok(userAnswer);
    }

    public ResponseEntity<?> getUserAnswerByExamId(Long examId) {
        List<UserAnswer> userAnswer = userAnswerRepository.findByExamId(examId);

        return ResponseEntity.ok(userAnswer);
    }

    public ResponseEntity<?> getUserAnswerByQuestionId(Long questionId) {
        List<UserAnswer> userAnswer = userAnswerRepository.findByQuestionId(questionId);

        return ResponseEntity.ok(userAnswer);
    }
}
