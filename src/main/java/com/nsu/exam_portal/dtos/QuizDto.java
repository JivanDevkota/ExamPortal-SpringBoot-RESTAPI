package com.nsu.exam_portal.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuizDto {
    private Long id;
    private String title;
    private String description;

    private Long category_id;
    private String categoryName;

    private boolean active;

    private int totalMarks;  //100
    private int noOfQuestion; //10

    private List<QuestionDto> questions;
}
