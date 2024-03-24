package com.deltadc.examsystem.UserAnswer;


import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Question.Question;
import com.deltadc.examsystem.User.User;
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "questionId")
    private Question question;
}
