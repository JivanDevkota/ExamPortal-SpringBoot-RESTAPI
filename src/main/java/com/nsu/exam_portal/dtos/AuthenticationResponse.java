package com.nsu.exam_portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {

    private String token;
    private String role;
    private String message;
    private long userId;
}
