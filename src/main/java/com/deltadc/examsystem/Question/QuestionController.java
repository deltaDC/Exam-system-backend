package com.deltadc.examsystem.Question;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("create-question/{examId}")
    public ResponseEntity<?> createQuestion(@RequestBody Question question, @PathVariable("examId") Long examId) {
        return questionService.createQuestion(question, examId);
    }

}