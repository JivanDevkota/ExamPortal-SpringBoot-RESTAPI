package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.QuestionDto;
import com.nsu.exam_portal.dtos.QuizDto;
import com.nsu.exam_portal.model.Question;
import com.nsu.exam_portal.model.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    public QuestionDto createQuestion(QuestionDto questionDto);

    public QuestionDto updateQuestion(Question question);

    public Set<QuestionDto>getAllQuestions();

    public QuestionDto getQuestionById(Long questionId);

//    public Set<QuestionDto>getQuestionOfQuiz(QuizDto quizDto);
    public Set<QuestionDto>getQuestionOfQuiz(Long quizId);

}
