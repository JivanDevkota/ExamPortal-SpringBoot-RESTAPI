package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.AuthenticationRequest;
import com.nsu.exam_portal.dtos.SignupRequest;
import com.nsu.exam_portal.dtos.UserDto;
import com.nsu.exam_portal.model.User;

public interface UserService {

    public UserDto registerUser(SignupRequest request);
    public String login(AuthenticationRequest request);

    User findByUsername(String username);
}
