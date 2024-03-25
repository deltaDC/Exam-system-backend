package com.deltadc.examsystem.Exam;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamDTO {

    private Long examId;

    private String examName;

    private String description;

    private String examType;

    private Date startTime;

    private Date endTime;

}
