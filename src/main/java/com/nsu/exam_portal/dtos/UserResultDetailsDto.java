package com.nsu.exam_portal.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResultDetailsDto {
    private Long resultId;
    private int score;
    private double numOfCorrectAnswer;
    private double numOfWrongAnswer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long quizId;
    private String quizTitle;
    private int totalMarks;
    private int noOfQuestions;

    private List<UserAnswerDto>userAnswerDtos;


}
