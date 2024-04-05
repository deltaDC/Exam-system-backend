package com.deltadc.examsystem.ExamStatistic;

import com.deltadc.examsystem.Exam.ExamRepository;
import com.deltadc.examsystem.ExamAttempt.ExamAttempt;
import com.deltadc.examsystem.ExamAttempt.ExamAttemptRepository;
import com.deltadc.examsystem.ExamAttempt.ExamAttemptService;
import com.deltadc.examsystem.ExamResult.ExamResult;
import com.deltadc.examsystem.ExamResult.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamStatisticService {

    private final ExamResultRepository examResultRepository;
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamStatisticRepository examStatisticRepository;

    public ResponseEntity<?> getExamStatisticByExamId(Long examId) {
        List<ExamAttempt> examAttempts = examAttemptRepository.findByExamId(examId);
        List<ExamResult> examResultList = examResultRepository.findByExamId(examId);

        double completionRate = (double) examResultList.size() / examAttempts.size();
        int totalParticipants = examAttempts.size();

        int totalScore = 0;
        for(ExamResult e : examResultList) {
            totalScore += e.getScore();
        }
        double averageScore = (double) totalScore / examResultList.size();

        ExamStatistic examStatistic = new ExamStatistic();
        examStatistic.setExamId(examId);
        examStatistic.setAverageScore(averageScore);
        examStatistic.setCompletionRate(completionRate);
        examStatistic.setTotalParticipants(totalParticipants);

//        examStatisticRepository.save(examStatistic);

        return ResponseEntity.ok(examStatistic);
    }
}
