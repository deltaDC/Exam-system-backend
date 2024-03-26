package com.deltadc.examsystem.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserId(Long userId);

    List<UserAnswer> findByExamId(Long examId);

    List<UserAnswer> findByQuestionId(Long questionId);
}
