package com.nsu.exam_portal.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResultDto {
    private String quizTitle;
    private int score;
    private double correctAnswer;
    private double wrongAnswer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
