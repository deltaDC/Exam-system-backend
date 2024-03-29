package com.deltadc.examsystem.UserAnswer;

import com.deltadc.examsystem.Question.Question;
import com.deltadc.examsystem.Question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<?> createUserAnswer(UserAnswer userAnswer) {
        UserAnswer newUserAnswer = new UserAnswer(
                userAnswer.getUserId(),
                userAnswer.getExamId(),
                userAnswer.getQuestionId(),
                userAnswer.getSelectedAnswer()
        );

        userAnswerRepository.save(newUserAnswer);

        Question question = questionRepository.findById(newUserAnswer.getQuestionId()).orElseThrow();
        String correctAnswer = question.getCorrectAnswer();

        if(correctAnswer.equalsIgnoreCase(userAnswer.getSelectedAnswer())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
//        return ResponseEntity.ok(newUserAnswer);
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

    public ResponseEntity<?> getUserAnswerInAnExam(Long examId, Long userId) {
        List<UserAnswer> userAnswer = userAnswerRepository.findByExamIdAndUserId(examId, userId);

        return ResponseEntity.ok(userAnswer);
    }

    public ResponseEntity<?> deleteUserAnswer(Long questionId, Long userId) {
        UserAnswer userAnswer = userAnswerRepository.findByQuestionIdAndUserId(questionId, userId);

        userAnswerRepository.delete(userAnswer);

        return ResponseEntity.ok("da xoa useranswer");
    }
}
