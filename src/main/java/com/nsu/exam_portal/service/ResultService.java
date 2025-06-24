package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.UserResultDetailsDto;

import java.util.List;

public interface ResultService {
    public List<UserResultDetailsDto>getResultByUser(Long userId);

    UserResultDetailsDto getDetailedResult(Long resultId);
}
