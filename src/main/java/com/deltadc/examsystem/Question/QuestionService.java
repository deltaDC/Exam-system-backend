package com.deltadc.examsystem.Question;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;

    public ResponseEntity<?> createQuestion(Question question, Long examId) {
        Exam exam = examRepository.findById(examId).orElse(null);

        if (exam == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ko tim thay exam theo id");
        }

        Question q = new Question();
        q.setExamId(examId);
        q.setQuestionText(question.getQuestionText());
        q.setAnswerOptionA(question.getAnswerOptionA());
        q.setAnswerOptionB(question.getAnswerOptionB());
        q.setAnswerOptionC(question.getAnswerOptionC());
        q.setAnswerOptionD(question.getAnswerOptionD());
        q.setCorrectAnswer(question.getCorrectAnswer());
        // nen tao rieng method

        questionRepository.save(q);

        return ResponseEntity.ok(q);
    }
}
