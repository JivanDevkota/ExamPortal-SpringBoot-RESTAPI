package com.nsu.exam_portal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nsu.exam_portal.dtos.AllQuizDto;
import com.nsu.exam_portal.dtos.QuestionDto;
import com.nsu.exam_portal.dtos.QuizDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    private String title;   //file handling
    private String description;  // something explaining about file handing topic
    private boolean active;

    private int totalMarks;  //100
    private int noOfQuestion; //10


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Result> results;


    public QuizDto toQuizDto(){
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quizId);
        quizDto.setTitle(title);
        quizDto.setDescription(description);
        quizDto.setActive(active);
        quizDto.setTotalMarks(totalMarks);
        quizDto.setNoOfQuestion(noOfQuestion);
        if (category != null) {
            quizDto.setCategory_id(category.getId());
            quizDto.setCategoryName(category.getName());
        }
        if (questions != null) {
            List<QuestionDto> question = questions.stream()
                    .map(Question::toQuestionDto)
                    .collect(Collectors.toList());
            quizDto.setQuestions(question);
        }
        return quizDto;
    }

    public AllQuizDto toAllQuizDto(){
        AllQuizDto allQuizDto = new AllQuizDto();
        allQuizDto.setId(quizId);
        allQuizDto.setTitle(title);
        allQuizDto.setDescription(description);
        allQuizDto.setActive(active);
        allQuizDto.setTotalMarks(totalMarks);
        allQuizDto.setNoOfQuestion(noOfQuestion);
        allQuizDto.setCategory_id(category.getId());
        allQuizDto.setCategoryName(category.getName());
        return allQuizDto;
    }
}
