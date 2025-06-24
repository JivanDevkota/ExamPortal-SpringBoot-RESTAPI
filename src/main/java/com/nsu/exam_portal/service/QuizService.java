package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.AllQuizDto;
import com.nsu.exam_portal.dtos.AttemptQuizRequestDto;
import com.nsu.exam_portal.dtos.AttemptQuizResponseDto;
import com.nsu.exam_portal.dtos.QuizDto;
import com.nsu.exam_portal.model.Quiz;

import java.util.List;

public interface QuizService {

    public QuizDto createQuiz(QuizDto quizDto);
    public List<QuizDto> getAllQuiz();

    List<AllQuizDto>AllQuiz();
    public AttemptQuizResponseDto attemptQuiz(Long userId, AttemptQuizRequestDto dto);
}
