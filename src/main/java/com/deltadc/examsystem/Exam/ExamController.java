package com.deltadc.examsystem.Exam;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
@CrossOrigin(origins = "http://localhost:3000")
public class ExamController {

    private final ExamService examService;

    //lay exam theo id
    @GetMapping("/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable("examId") Long examId) {
        return examService.getExamById(examId);
    }

    //lay tat ca cac exam dang co
    @GetMapping("/get-all-exams")
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

    //edit exam theo examID
    @PutMapping("/edit/{examId}")
    public ResponseEntity<?> editExam(@PathVariable("examId") Long examId, @RequestBody Exam newExam) {
        return examService.editExamById(examId, newExam);
    }

    //tim kiem exam theo examName
    @GetMapping("/exam-name/{examName}")
    public ResponseEntity<?> getExamByExamName(@PathVariable("examName") String examName) {
        return examService.getExamByExamName(examName);
    }

    //tim kiem exam theo examType
    @GetMapping("/exam-type/{examType}")
    public ResponseEntity<?> getExamByExamType(@PathVariable("examType") String examType) {
        return examService.getExamByExamType(examType);
    }
}
