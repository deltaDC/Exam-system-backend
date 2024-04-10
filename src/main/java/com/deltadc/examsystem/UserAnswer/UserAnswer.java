package com.deltadc.examsystem.UserAnswer;


import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Question.Question;
import com.deltadc.examsystem.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "UserAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAnswerId;

    private String selectedAnswer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examId", insertable = false, updatable = false)
    private Exam exam;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId", insertable = false, updatable = false)
    private Question question;

    private Long userId;
    private Long examId;
    private Long questionId;

    public UserAnswer(Long userId, Long examId, Long questionId, String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        this.userId = userId;
        this.examId = examId;
        this.questionId = questionId;
    }
}
