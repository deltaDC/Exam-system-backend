package com.deltadc.examsystem.Exam;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public ResponseEntity<?> getExamById(Long examId) {
        Exam exam = examRepository.findById(examId).orElse(null);

        if (exam == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ko tim thay exam theo id");
        }

        return ResponseEntity.ok(exam);
    }

    public ResponseEntity<?> createExam(Exam exam) {
        Exam newExam = new Exam();
        newExam.setExamName(exam.getExamName());
        newExam.setDescription(exam.getDescription());
        newExam.setExamType(exam.getExamType());
        newExam.setStartTime(exam.getStartTime());
        newExam.setEndTime(exam.getEndTime());

        examRepository.save(newExam);

        return ResponseEntity.ok(newExam);
    }
}
