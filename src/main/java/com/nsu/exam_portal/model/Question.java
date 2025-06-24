package com.nsu.exam_portal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nsu.exam_portal.dtos.QuestionDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    public QuestionDto toQuestionDto() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(id);
        questionDto.setTitle(question);

        questionDto.setOptions(getOptions());
        questionDto.setCorrectAnswer(correctAnswer);
        questionDto.setQuizId(quiz.getQuizId());
        questionDto.setQuizTitle(quiz.getTitle());
        return questionDto;

    }

    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        return options;
    }

}
