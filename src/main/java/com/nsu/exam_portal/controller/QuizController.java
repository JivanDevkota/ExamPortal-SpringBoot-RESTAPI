package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.AllQuizDto;
import com.nsu.exam_portal.dtos.QuizDto;
import com.nsu.exam_portal.model.Quiz;
import com.nsu.exam_portal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @GetMapping("/teacher/all/quiz")
    public ResponseEntity<List<QuizDto>>getAllQuiz(){
        List<QuizDto> allQuiz = quizService.getAllQuiz();
        return new ResponseEntity<>(allQuiz, HttpStatus.OK);
    }

    @GetMapping("/teacher/all-quizzes")
    public ResponseEntity<?>getQuiz(){
        List<AllQuizDto> allQuizDtos = quizService.AllQuiz();
        return new ResponseEntity<>(allQuizDtos, HttpStatus.OK);
    }
}
