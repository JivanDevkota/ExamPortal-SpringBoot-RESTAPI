package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.*;
import com.nsu.exam_portal.service.QuestionService;
import com.nsu.exam_portal.service.QuizService;
import com.nsu.exam_portal.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/student/all-quiz")
    public ResponseEntity<List<AllQuizDto>> getAllQuizDto(){
        List<AllQuizDto> allQuiz = quizService.AllQuiz();
        return new ResponseEntity<>(allQuiz, HttpStatus.OK);
    }

    @PostMapping("/student/submit/{userId}")
    public ResponseEntity<AttemptQuizResponseDto> submitQuiz(@RequestBody AttemptQuizRequestDto dto,
                                                             @PathVariable Long userId) {
        AttemptQuizResponseDto response = quizService.attemptQuiz(userId, dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/student/quiz/{quizId}/questions")
    public ResponseEntity<?>getAllQuestionForQuiz(@PathVariable Long quizId) {
        Set<QuestionDto> questionOfQuiz = questionService.getQuestionOfQuiz(quizId);
        return new ResponseEntity<>(questionOfQuiz, HttpStatus.OK);
    }

    @GetMapping("/student/{userId}/result")
    public ResponseEntity<List<UserResultDetailsDto>>getUserResults(@PathVariable Long userId) {
        List<UserResultDetailsDto> resultByUser = resultService.getResultByUser(userId);
        return new ResponseEntity<>(resultByUser, HttpStatus.OK);
    }

    @GetMapping("/student/result/{resultId}/details")
    public ResponseEntity<UserResultDetailsDto> getUserResult(@PathVariable Long resultId) {
        UserResultDetailsDto detailedResult = resultService.getDetailedResult(resultId);
        return new ResponseEntity<>(detailedResult, HttpStatus.OK);
    }
}
