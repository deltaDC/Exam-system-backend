package com.deltadc.examsystem.ExamStatistic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-statistic")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ExamStatisticController {

    private final ExamStatisticService examStatisticService;

    //lay thong ke cua mot ki thi dua theo examId
    @GetMapping("/{examId}")
    public ResponseEntity<?> getExamStatisticByExamId(@PathVariable("examId") Long examId) {
        return examStatisticService.getExamStatisticByExamId(examId);
    }

}
