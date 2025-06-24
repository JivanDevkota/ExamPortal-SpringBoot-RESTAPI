package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.QuestionDto;
import com.nsu.exam_portal.dtos.QuizDto;
import com.nsu.exam_portal.model.Question;
import com.nsu.exam_portal.model.Quiz;
import com.nsu.exam_portal.repository.QuestionRepository;
import com.nsu.exam_portal.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        if (questionDto==null){
            throw new IllegalArgumentException("questionDto must not be null");
        }

        Question question = new Question();
        question.setQuestion(questionDto.getTitle());
        List<String>options = questionDto.getOptions();

        question.setOption1(options.get(0));
        question.setOption2(options.get(1));
        question.setOption3(options.get(2));
        question.setOption4(options.get(3));
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        Quiz quiz = quizRepository.findById(questionDto.getQuizId())
                .orElseThrow(()-> new IllegalArgumentException("quiz not found with this quizId"+questionDto.getQuizId()));
        question.setQuiz(quiz);
        return questionRepository.save(question).toQuestionDto();
    }

    @Override
    public QuestionDto updateQuestion(Question questionUpdate) {
        Question existing = questionRepository.findById(questionUpdate.getId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found with ID: " + questionUpdate.getId()));

        existing.setQuestion(questionUpdate.getQuestion());
        existing.setOption1(questionUpdate.getOption1());
        existing.setOption2(questionUpdate.getOption2());
        existing.setOption3(questionUpdate.getOption3());
        existing.setOption4(questionUpdate.getOption4());
        existing.setCorrectAnswer(questionUpdate.getCorrectAnswer());

        return questionRepository.save(existing).toQuestionDto();
    }

    @Override
    public Set<QuestionDto> getAllQuestions() {
        Set<QuestionDto> questionDtoSet = new HashSet<>();
        questionRepository.findAll().forEach(q->questionDtoSet.add(q.toQuestionDto()));
        return questionDtoSet;
    }

    @Override
    public QuestionDto getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .map(Question::toQuestionDto)
                .orElseThrow(()-> new IllegalArgumentException("Question not found with ID: " + questionId));
    }

//    @Override
//    public Set<QuestionDto> getQuestionOfQuiz(QuizDto quizDto) {
//        Optional<Question> quiz = questionRepository.findById(quizDto.getId());
//        if (quiz.isEmpty()) {
//            throw new IllegalArgumentException("Quiz not found with ID: " + quizDto.getId());
//        }
//        Set<QuestionDto> questionDtoSet = new HashSet<>();
//        for (Question q:quiz.get().getQuiz().getQuestions()){
//            questionDtoSet.add(q.toQuestionDto());
//        }
//        return questionDtoSet;
//    }
//@Override
//public Set<QuestionDto> getQuestionOfQuiz(QuizDto quizDto) {
//    if (quizDto == null || quizDto.getId() == null) {
//        throw new IllegalArgumentException("QuizDto or quiz ID must not be null");
//    }
//
//    Quiz quiz = quizRepository.findById(quizDto.getId())
//            .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizDto.getId()));
//
//    Set<QuestionDto> questionDtoSet = new HashSet<>();
//    for (Question q : quiz.getQuestions()) {
//        questionDtoSet.add(q.toQuestionDto());
//    }
//
//    return questionDtoSet;
//}

    public Set<QuestionDto>getQuestionOfQuiz(Long quizId){
        Quiz quiz= quizRepository.findById(quizId)
                .orElseThrow(()-> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        Set<QuestionDto> questions = new HashSet<>();
        for (Question q:quiz.getQuestions()){
            questions.add(q.toQuestionDto());
        }
        return questions;
    }

}
