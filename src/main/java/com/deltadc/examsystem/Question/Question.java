package com.deltadc.examsystem.Question;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.UserAnswer.UserAnswer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String questionText;

    private String answerOptionA;

    private String answerOptionB;

    private String answerOptionC;

    private String answerOptionD;

    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "questionId")
    private UserAnswer userAnswer;
}
