package com.nsu.exam_portal.model;

import com.nsu.exam_portal.dtos.UserResultDetailsDto;
import com.nsu.exam_portal.dtos.UserAnswerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private double numOfCorrectAnswers;  //5
    private double numOfWrongAnswers;  //5


    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "result",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserAnswer>userAnswers;

    public UserResultDetailsDto getUserResultDto() {
        UserResultDetailsDto userResultDto = new UserResultDetailsDto();
        userResultDto.setResultId(id);
        userResultDto.setScore(score);
        userResultDto.setNumOfCorrectAnswer(numOfCorrectAnswers);
        userResultDto.setNumOfWrongAnswer(numOfWrongAnswers);
        userResultDto.setStartTime(startTime);
        userResultDto.setEndTime(endTime);

        if (this.quiz != null) {
            userResultDto.setQuizId(quiz.getQuizId());
            userResultDto.setQuizTitle(quiz.getTitle());
            userResultDto.setTotalMarks(quiz.getTotalMarks());
            userResultDto.setNoOfQuestions(quiz.getQuestions().size());
        }

        if (userAnswers != null) {
            List<UserAnswerDto> answerDtos = userAnswers.stream().map(ua -> {
                UserAnswerDto uaDto = new UserAnswerDto();
                uaDto.setQuestionId(ua.getQuestion().getId());
                uaDto.setQuestion(ua.getQuestion().getQuestion());
                uaDto.setOptions(List.of(
                        ua.getQuestion().getOption1(),
                        ua.getQuestion().getOption2(),
                        ua.getQuestion().getOption3(),
                        ua.getQuestion().getOption4()
                ));
                uaDto.setSelectedAnswer(ua.getSelectedAnswer());
                uaDto.setCorrectAnswer(ua.getQuestion().getCorrectAnswer());
                return uaDto;
            }).toList();

            userResultDto.setUserAnswerDtos(answerDtos);
        }
        return userResultDto;
    }
}
