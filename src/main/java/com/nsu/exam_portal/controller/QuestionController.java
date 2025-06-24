package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.QuestionDto;
import com.nsu.exam_portal.dtos.QuizDto;
import com.nsu.exam_portal.model.Question;
import com.nsu.exam_portal.model.Quiz;
import com.nsu.exam_portal.repository.QuizRepository;
import com.nsu.exam_portal.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping("/teacher/create/question")
    public ResponseEntity<?> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto question = questionService.createQuestion(questionDto);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @GetMapping("/teacher/quiz/{quizId}/questions")
    public ResponseEntity<?>getAllQuestionForQuiz(@PathVariable Long quizId) {
        Set<QuestionDto> questionOfQuiz = questionService.getQuestionOfQuiz(quizId);
        return new ResponseEntity<>(questionOfQuiz, HttpStatus.OK);
    }
}
