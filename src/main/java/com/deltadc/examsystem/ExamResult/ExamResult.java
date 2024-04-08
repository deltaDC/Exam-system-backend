package com.deltadc.examsystem.ExamResult;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ExamResult")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examResultId;

    private int score;

    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "examId", insertable = false, updatable=false)
    private Exam exam;

    private Long userId;

    private Long examId;

    public ExamResult(Long userId, Long examId, int score, String status) {
        this.score = score;
        this.status = status;
        this.userId = userId;
        this.examId = examId;
    }
}
