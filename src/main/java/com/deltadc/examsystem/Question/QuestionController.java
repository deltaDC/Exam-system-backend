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

    //tao question moi
    @PostMapping("/create-question/{examId}")
    public ResponseEntity<?> createQuestion(@RequestBody Question question, @PathVariable("examId") Long examId) {
        return questionService.createQuestion(question, examId);
    }

    //lay question theo id
    @GetMapping("/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId") Long questionId) {
        return questionService.getQuestionById(questionId);
    }

}
