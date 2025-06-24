package com.nsu.exam_portal.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {
    private Long questionId;
    private String title;
    private List<String> options;
//    private String option1;
//    private String option2;
//    private String option3;
//    private String option4;
    private String correctAnswer;

    private Long quizId;
    private String quizTitle;
}
