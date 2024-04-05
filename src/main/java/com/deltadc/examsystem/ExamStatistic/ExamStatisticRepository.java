package com.deltadc.examsystem.ExamStatistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamStatisticRepository extends JpaRepository<ExamStatistic, Long> {
}
