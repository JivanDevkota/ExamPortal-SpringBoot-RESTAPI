package com.nsu.exam_portal.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AttemptQuizResponseDto {
    private int score;
    private double correctAnswer;
    private double wrongAnswer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
