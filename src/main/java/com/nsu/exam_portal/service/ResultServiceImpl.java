package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.UserResultDetailsDto;
import com.nsu.exam_portal.model.Result;
import com.nsu.exam_portal.model.User;
import com.nsu.exam_portal.repository.ResultRepository;
import com.nsu.exam_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResultDetailsDto> getResultByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        List<Result> userResult = resultRepository.findByUser(user);
        return userResult.stream()
                .map(Result::getUserResultDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResultDetailsDto getDetailedResult(Long resultId) {
        Result userResult = resultRepository.findById(resultId).orElseThrow(() -> new RuntimeException("Result Not Found"));

        return userResult.getUserResultDto();
    }
}
