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

    //tao exam moi
    @PostMapping("/create-exam")
    public ResponseEntity<?> createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }
}
