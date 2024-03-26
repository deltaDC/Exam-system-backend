package com.deltadc.examsystem.UserAnswer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-answer")
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

}
