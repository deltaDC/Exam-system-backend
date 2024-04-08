package com.deltadc.examsystem.ExamStatistic;

import com.deltadc.examsystem.Exam.Exam;
import com.deltadc.examsystem.Exam.ExamRepository;
import com.deltadc.examsystem.ExamAttempt.ExamAttempt;
import com.deltadc.examsystem.ExamAttempt.ExamAttemptRepository;
import com.deltadc.examsystem.ExamAttempt.ExamAttemptService;
import com.deltadc.examsystem.ExamResult.ExamResult;
import com.deltadc.examsystem.ExamResult.ExamResultRepository;
import com.deltadc.examsystem.User.User;
import com.deltadc.examsystem.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamStatisticService {

    private final ExamResultRepository examResultRepository;
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getExamStatisticByExamId() {
        List<Exam> exams = examRepository.findAll();
        List<ExamStatistic> examStatisticList = new ArrayList<>();

        for(Exam exam : exams) {
            long examId = exam.getExamId();
            List<ExamAttempt> examAttempts = examAttemptRepository.findByExamId(examId);
            List<ExamResult> examResultList = examResultRepository.findByExamId(examId);
            List<User> userList = userRepository.findByRoleContaining("USER");

            double completionRate;

            if(userList.size() > 0) {
                completionRate = (double) examResultList.size() / userList.size();
            } else {
                completionRate = 0.0;
            }
            completionRate *= 100;
            if(completionRate > 100) {
                completionRate = 100;
            }
            int totalParticipants = examAttempts.size();

            int totalScore = 0;
            for(ExamResult e : examResultList) {
                totalScore += e.getScore();
            }

            double averageScore;
            if(examResultList.size() > 0) {
                averageScore = (double) totalScore / examResultList.size();
            } else {
                averageScore = 0.0;
            }


            ExamStatistic examStatistic = new ExamStatistic();
            examStatistic.setExamName(exam.getExamName());
            examStatistic.setAverageScore(averageScore);
            examStatistic.setCompletionRate(completionRate);
            examStatistic.setTotalParticipants(totalParticipants);

            examStatisticList.add(examStatistic);
        }


        return ResponseEntity.ok(examStatisticList);
    }
}
