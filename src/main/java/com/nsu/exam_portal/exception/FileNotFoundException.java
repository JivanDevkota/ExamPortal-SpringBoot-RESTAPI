package com.nsu.exam_portal.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class FileNotFoundException extends RuntimeException {

    private String message;
    public FileNotFoundException() {}

    public FileNotFoundException(String message) {
        super();
        this.message = message;
    }
}
