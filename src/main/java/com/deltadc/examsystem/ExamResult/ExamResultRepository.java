package com.deltadc.examsystem.ExamResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findByUserId(Long userId);

    List<ExamResult> findByExamId(Long examId);

    List<ExamResult> findByUserIdAndExamId(Long userId, Long examId);
}
