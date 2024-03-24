package com.deltadc.examsystem.ExamAttempt;


import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ExamAttempt")
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examAttemptId;

    private Date startTime;

    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;
}
