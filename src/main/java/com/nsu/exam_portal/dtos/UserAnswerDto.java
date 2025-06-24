package com.nsu.exam_portal.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAnswerDto {
    private Long questionId;
    private String question;
    private List<String>options;
    private String selectedAnswer;
    private String correctAnswer;

}
