package com.deltadc.examsystem.ExamAttempt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-attempt")
@RequiredArgsConstructor
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    //tao exam attempt moi
    @PostMapping("/create-exam-attempt")
    public ResponseEntity<?> createExamAttempt(@RequestBody ExamAttempt examAttempt) {
        return examAttemptService.createExamAttempt(examAttempt);
    }

    //lay exam attempt theo id
    @GetMapping("/{examAttemptId}")
    public ResponseEntity<?> getExamAttemptById(@PathVariable("examAttemptId") Long examAttemptId) {
        return examAttemptService.getExamAttemptById(examAttemptId);
    }

    //edit exam attempt
    @PutMapping("/{examAttemptId}")
    public ResponseEntity<?> editExamAttemptById(@PathVariable("examAttemptId") Long examAttemptId, @RequestBody ExamAttempt newExamAttempt) {
        return examAttemptService.editExamAttemptById(examAttemptId, newExamAttempt);
    }

    //lay exam attempt theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getExamAttemptByUserId(@PathVariable("userId") Long userId) {
        return examAttemptService.getExamAttemptByUserId(userId);
    }

    //lay exam attempt theo examId
    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> getExamAttemptByExamId(@PathVariable("examId") Long examId) {
        return examAttemptService.getExamAttemptByExamId(examId);
    }

    //lay tong so luong nguoi dung tham gia moi ki thi theo examId (tinh ca chua hoan thanh)
    @GetMapping("/exam/{examId}/total-attempts")
    public ResponseEntity<?> getTotalAttempts(@PathVariable("examId") Long examId) {
        return examAttemptService.getTotalAttempts(examId);
    }

}
