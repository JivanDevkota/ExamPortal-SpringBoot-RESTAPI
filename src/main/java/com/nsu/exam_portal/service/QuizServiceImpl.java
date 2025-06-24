package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.*;
import com.nsu.exam_portal.model.*;
import com.nsu.exam_portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResultRepository resultRepository;


    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
       return null;
    }

    @Override
    public List<QuizDto> getAllQuiz() {
        List<Quiz> quizList = quizRepository.findAll();
        return quizList.stream()
                .map(Quiz::toQuizDto)
                .collect(Collectors.toList());
    }

   public List<AllQuizDto>AllQuiz(){
        List<Quiz> quizList = quizRepository.findAll();
    return quizList.stream()
            .map(Quiz::toAllQuizDto)
            .collect(Collectors.toList());
    }

    @Override
    public AttemptQuizResponseDto attemptQuiz(Long userId, AttemptQuizRequestDto dto) {
        Quiz quiz = quizRepository.findById(dto.getQuizId())
                .orElseThrow(() -> new RuntimeException("quiz not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        LocalDateTime startTime = LocalDateTime.now();

        int correct = 0;
        int wrong = 0;
        int marksPerQuestion = quiz.getTotalMarks() / quiz.getNoOfQuestion();

        List<UserAnswer> userAnswers = new ArrayList<>();

        for (Map.Entry<Long, String> entry : dto.getQuestionAnswer().entrySet()) {
            Long questionId = entry.getKey();
            String selectedAnswer = entry.getValue();

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question"));

            String correctAnswer = question.getCorrectAnswer();
            boolean isCorrect = correctAnswer.equalsIgnoreCase(selectedAnswer);

            if (isCorrect) {
                correct++;
            } else {
                wrong++;
            }

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setQuestion(question);
            userAnswer.setSelectedAnswer(selectedAnswer);
            userAnswers.add(userAnswer);
        }

        int score = correct * marksPerQuestion;
        LocalDateTime endTime = LocalDateTime.now();

        // Build Result and associate UserAnswers
        Result result = new Result();
        result.setQuiz(quiz);
        result.setUser(user);
        result.setScore(score);
        result.setNumOfCorrectAnswers(correct);
        result.setNumOfWrongAnswers(wrong);
        result.setStartTime(startTime);
        result.setEndTime(endTime);

        for (UserAnswer userAnswer : userAnswers) {
            userAnswer.setResult(result); // Set back-reference
        }

        result.setUserAnswers(userAnswers); // Set all user answers to result

        resultRepository.save(result); // Saves both Result and UserAnswers via cascade

        return AttemptQuizResponseDto.builder()
                .score(score)
                .correctAnswer(correct)
                .wrongAnswer(wrong)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }


//    public AttemptQuizResponseDto attemptQuiz(Long userId, AttemptQuizRequestDto dto) {
//        Quiz quiz = quizRepository.findById(dto.getQuizId())
//                .orElseThrow(() -> new RuntimeException("Quiz not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        LocalDateTime startTime = LocalDateTime.now();
//
//        int correct = 0;
//        int wrong = 0;
//        int marksPerQuestion = quiz.getTotalMarks() / quiz.getNoOfQuestion();
//
//        for (Map.Entry<Long, String> entry : dto.getQuestionAnswer().entrySet()) {
//            Question question = questionRepository.findById(entry.getKey())
//                    .orElseThrow(() -> new RuntimeException("Question not found"));
//
//            String selected = entry.getValue();
//            if (question.getCorrectAnswer().equalsIgnoreCase(selected)) {
//                correct++;
//            } else {
//                wrong++;
//            }
//        }
//
//        int score = correct * marksPerQuestion;
//        LocalDateTime endTime = LocalDateTime.now();
//
//        Result result = Result.builder()
//                .quiz(quiz)
//                .user(user)
//                .score(score)
//                .numOfCorrectAnswers(correct)
//                .numOfWrongAnswers(wrong)
//                .startTime(startTime)
//                .endTime(endTime)
//                .build();
//
//        resultRepository.save(result);
//
//        return AttemptQuizResponseDto.builder()
//                .score(score)
//                .correctAnswer(correct)
//                .wrongAnswer(wrong)
//                .startTime(startTime)
//                .endTime(endTime)
//                .build();
//    }
//public AttemptQuizResponseDto attemptQuiz(Long userId, AttemptQuizRequestDto dto) {
//    Quiz quiz = quizRepository.findById(dto.getQuizId())
//            .orElseThrow(() -> new RuntimeException("Quiz not found"));
//
//    User user = userRepository.findById(userId)
//            .orElseThrow(() -> new RuntimeException("User not found"));
//
//    LocalDateTime startTime = LocalDateTime.now();
//
//    int correct = 0;
//    int wrong = 0;
//    int marksPerQuestion = quiz.getTotalMarks() / quiz.getNoOfQuestion();
//
//    List<UserAnswerDto> answerDetails = dto.getQuestionAnswer().entrySet().stream()
//            .map(entry -> {
//                Long questionId = entry.getKey();
//                String selectedAnswer = entry.getValue();
//
//                Question question = questionRepository.findById(questionId)
//                        .orElseThrow(() -> new RuntimeException("Question not found"));
//
//                String correctAnswer = question.getCorrectAnswer();
//                boolean isCorrect = correctAnswer.equalsIgnoreCase(selectedAnswer);
//
//                if (isCorrect)correct++;
//                else wrong++;
//
//                return new UserAnswerDto(
//                        question.getId(),
//                        question.getQuestion(),
//                        question.getOptions(),  // Assuming getOptions() returns List<String>
//                        selectedAnswer,
//                        correctAnswer
//                );
//            }).collect(Collectors.toList());
//
//    int score = correct * marksPerQuestion;
//    LocalDateTime endTime = LocalDateTime.now();
//
//    Result result = Result.builder()
//            .quiz(quiz)
//            .user(user)
//            .score(score)
//            .numOfCorrectAnswers(correct)
//            .numOfWrongAnswers(wrong)
//            .startTime(startTime)
//            .endTime(endTime)
////            .userAnswerDtoo(answerDetails) // Save detailed answers if mapped in entity
//            .build();
//
//    resultRepository.save(result);
//
//    return AttemptQuizResponseDto.builder()
//            .score(score)
//            .correctAnswer(correct)
//            .wrongAnswer(wrong)
//            .startTime(startTime)
//            .endTime(endTime)
//            .build();
//}


}
