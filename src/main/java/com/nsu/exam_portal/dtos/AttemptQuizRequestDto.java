package com.nsu.exam_portal.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class AttemptQuizRequestDto {
    private Long quizId;
    private Map<Long,String>questionAnswer;   //questionId---->selectedAnswer
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
