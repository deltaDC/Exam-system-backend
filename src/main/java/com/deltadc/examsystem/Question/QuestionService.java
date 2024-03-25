package com.deltadc.examsystem.Question;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> getQuestionById(Long questionId) {
//        Question q = questionRepository.findById(questionId).orElseThrow();
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        return ResponseEntity.ok(optionalQuestion);
    }

    public ResponseEntity<?> getAllQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return ResponseEntity.ok(questionList);
    }

    public ResponseEntity<?> getQuestionsByExamId(Long examId) {
        List<Question> questionList = questionRepository.findByExamId(examId);
        return ResponseEntity.ok(questionList);
    }

    public ResponseEntity<?> deleteQuestionById(Long questionId) {
        Question q = questionRepository.findById(questionId).orElseThrow();
        questionRepository.deleteById(questionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("da xoa question theo id");
    }

    public ResponseEntity<?> editQuestionById(Long questionId, Question newQuestion) {
        Question q = questionRepository.findById(questionId).orElseThrow();

        q.setExamId(newQuestion.getExamId());
        q.setQuestionText(newQuestion.getQuestionText());
        q.setAnswerOptionA(newQuestion.getAnswerOptionA());
        q.setAnswerOptionB(newQuestion.getAnswerOptionB());
        q.setAnswerOptionC(newQuestion.getAnswerOptionC());
        q.setAnswerOptionD(newQuestion.getAnswerOptionD());
        q.setCorrectAnswer(newQuestion.getCorrectAnswer());

        questionRepository.save(q);

        return ResponseEntity.ok(q);
    }
}
