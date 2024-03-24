package com.deltadc.examsystem.ExamStatistic;


import com.deltadc.examsystem.Exam.Exam;
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
@Table(name = "ExamStatistic")
public class ExamStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examStatisticId;

    private int totalParticipants;

    private double completionRate;

    private double averageScore;

    @OneToOne
    @JoinColumn(name = "examId")
    private Exam exam;
}
