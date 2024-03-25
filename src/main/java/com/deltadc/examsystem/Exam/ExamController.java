package com.deltadc.examsystem.Exam;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    //lay exam theo id
    @GetMapping("/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable("examId") Long examId) {
        return examService.getExamById(examId);
    }

    //lay tat ca cac exam dang co
    @GetMapping("get-all-exams")
    public ResponseEntity<?> getAllExams() {
        return examService.getAllExams();
    }

    //tao exam moi
    @PostMapping("/create-exam")
    public ResponseEntity<?> createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    //xoa exam theo id
    @DeleteMapping("/delete-exam/{examId}")
    public ResponseEntity<?> deleteExamById(@PathVariable("examId") Long examId) {
        return examService.deleteExamById(examId);
    }
}
