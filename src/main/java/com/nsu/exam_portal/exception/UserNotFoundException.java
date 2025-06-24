package com.nsu.exam_portal.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserNotFoundException extends RuntimeException{

    private String message;

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
      super(message);
      this.message = message;
    }
}
