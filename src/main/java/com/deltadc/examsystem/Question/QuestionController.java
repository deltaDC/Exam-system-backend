package com.deltadc.examsystem.Question;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    private final QuestionService questionService;

    //tao question moi
    @PostMapping("/create-question/{examId}")
    public ResponseEntity<?> createQuestion(@RequestBody Question question, @PathVariable("examId") Long examId) {
        return questionService.createQuestion(question, examId);
    }

    //lay question theo id
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId") Long questionId) {
        return questionService.getQuestionById(questionId);
    }

    //lay tat ca cac question
    @GetMapping("/get-all-questions")
    public ResponseEntity<?> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    //lay question theo tung exam
    @GetMapping("/get-all-questions/{examId}")
    public ResponseEntity<?> getQuestionsByExamId(@PathVariable("examId") Long examId) {
        return questionService.getQuestionsByExamId(examId);
    }

    //xoa question theo id
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("questionId") Long questionId) {
        return questionService.deleteQuestionById(questionId);
    }

    //edit question theo id
    @PutMapping("/edit/{questionId}")
    public ResponseEntity<?> editQuestionById(@PathVariable("questionId") Long questionId, @RequestBody Question newQuestion) {
        return questionService.editQuestionById(questionId, newQuestion);
    }

}
