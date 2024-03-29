package com.deltadc.examsystem.UserAnswer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-answer")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAnswerController {

    private final UserAnswerService userAnswerService;

    //tao useranswer moi
    @PostMapping("/create-user-answer")
    public ResponseEntity<?> createUserAnswer(@RequestBody UserAnswer userAnswer) {
        return userAnswerService.createUserAnswer(userAnswer);
    }

    //lay user answer theo id
    @GetMapping("/{userAnswerId}")
    public ResponseEntity<?> getUserAnswerById(@PathVariable("userAnswerId") Long userAnswerId) {
        return userAnswerService.getUserAnswerById(userAnswerId);
    }

    //lay user answer theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserAnswerByUserId(@PathVariable("userId") Long userId) {
        return userAnswerService.getUserAnswerByUserId(userId);
    }

    //lay user answer theo examId
    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> getUserAnswerByExamId(@PathVariable("examId") Long examId) {
        return userAnswerService.getUserAnswerByExamId(examId);
    }

    //lay user answer theo questionId
    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getUserAnswerByQuestionId(@PathVariable("questionId") Long questionId) {
        return userAnswerService.getUserAnswerByQuestionId(questionId);
    }

    //lay useranswer theo userId va examId (dap an cua nguoi dung trong 1 bai kiem tra)
    @GetMapping("/exam/{examId}/user/{userId}")
    public ResponseEntity<?> getUserAnswerInAnExam(@PathVariable("examId") Long examId, @PathVariable("userId") Long userId) {
        return userAnswerService.getUserAnswerInAnExam(examId, userId);
    }

    //xoa useranswer theo userId va questionId
    @DeleteMapping("/delete/question/{questionId}/user/{userId}")
    public ResponseEntity<?> deleteUserAnswer(@PathVariable("questionId") Long questionId, @PathVariable("userId") Long userId) {
        return userAnswerService.deleteUserAnswer(questionId, userId);
    }

}
