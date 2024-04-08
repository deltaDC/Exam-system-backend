package com.deltadc.examsystem.ExamAttempt;


import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC+7")
    private Date endTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
    private Exam exam;

    private Long userId;
    private Long examId;

    public ExamAttempt( Long userId, Long examId, Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.examId = examId;
    }
}
