package com.deltadc.examsystem.ExamResult;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-result")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;

    //tao exam result moi
    @PostMapping("/create-exam-result")
    public ResponseEntity<?> createExamResult(@RequestBody ExamResult examResult) {
        return examResultService.createExamResult(examResult);
    }

    //lay toan bo exam result
    @GetMapping("/get-exam-results")
    public ResponseEntity<?> getAllExamResults() {
        return examResultService.getAllExamResults();
    }

    //lay exam result theo examResultId
    @GetMapping("/{examResultId}")
    public ResponseEntity<?> getExamResultsById(@PathVariable("examResultId") Long examResultId) {
        return examResultService.getExamResultsById(examResultId);
    }

    //lay exam result theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getExamResultsByUserId(@PathVariable("userId") Long userId) {
        return examResultService.getExamResultByUserId(userId);
    }

    //lay exam result theo examId
    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> getExamResultsByExamId(@PathVariable("examId") Long examId) {
        return examResultService.getExamResultByExamId(examId);
    }

    //lay exam result theo ca userId va examId
    @GetMapping("/{userId}/{examId}")
    public ResponseEntity<?> getExamResultsByUserIdExamId(@PathVariable("userId") Long userId, @PathVariable("examId") Long examId) {
        return examResultService.getExamResultsByUserIdExamId(userId, examId);
    }

    //xoa exam result theo examResultId
    @DeleteMapping("/delete/{examResultId}")
    public ResponseEntity<?> deleteExamResultById(@PathVariable("examResultId") Long examResultId) {
        return examResultService.deleteExamResultById(examResultId);
    }

    //sua exam result theo examResultId
    @PutMapping("/edit/{examResultId}")
    public ResponseEntity<?> editExamResultById(@PathVariable("examResultId") Long examResultId, @RequestBody ExamResult newExamResult) {
        return examResultService.editExamResultById(examResultId, newExamResult);
    }

    //lay diem trung binh cua mot exam
    @GetMapping("/exam/{examId}/average-score")
    public ResponseEntity<?> getExamAverageScore(@PathVariable("examId") Long examId) {
        return examResultService.getExamAverageScore(examId);
    }
}
