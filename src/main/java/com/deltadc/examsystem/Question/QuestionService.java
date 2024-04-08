package com.deltadc.examsystem.Question;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

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
        Exam exam = examRepository.findById(examId).orElseThrow();
//        System.out.println(exam.getStartTime());
//        System.out.println(exam.getEndTime());
//
//        List<Question> questionList = questionRepository.findByExamId(examId);
//        return ResponseEntity.ok(questionList);

        Date currentTime = new Date();
        Date startTime = exam.getStartTime();
        Date endTime = exam.getEndTime();


        // Define date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        // Format dates to strings
        String currentTimeStr = sdf.format(currentTime);
        String startTimeStr = sdf.format(startTime);
        String endTimeStr = sdf.format(endTime);

        System.out.println(currentTimeStr);
        System.out.println(startTimeStr);
        System.out.println(endTimeStr);


        if (currentTimeStr.compareTo(startTimeStr) >= 0 && currentTimeStr.compareTo(endTimeStr) < 0) {
            List<Question> questionList = questionRepository.findByExamId(examId);
            return ResponseEntity.ok(questionList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ngoai thoi gian thi");
        }
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
