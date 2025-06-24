package com.nsu.exam_portal.dtos;

import lombok.Data;

@Data
public class AllQuizDto {
    private Long id;
    private String title;
    private String description;

    private Long category_id;
    private String categoryName;

    private boolean active;

    private int totalMarks;  //100
    private int noOfQuestion; //10
}
