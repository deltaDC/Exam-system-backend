package com.deltadc.examsystem.Exam;

import com.deltadc.examsystem.ExamAttempt.ExamAttempt;
import com.deltadc.examsystem.ExamResult.ExamResult;
import com.deltadc.examsystem.ExamStatistic.ExamStatistic;
import com.deltadc.examsystem.Question.Question;
import com.deltadc.examsystem.UserAnswer.UserAnswer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String examName;

    private String description;

    private String examType;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date endTime;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamResult> examResults;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamAttempt> examAttempts;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL)
    private ExamStatistic examStatistic;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;
}
