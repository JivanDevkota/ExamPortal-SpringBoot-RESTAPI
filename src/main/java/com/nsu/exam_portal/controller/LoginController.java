package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.AuthenticationRequest;
import com.nsu.exam_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {


    @Autowired
    private UserService userService;


    @PostMapping("/api/login")
    public ResponseEntity<?>login(@RequestBody AuthenticationRequest request){
        String login = userService.login(request);
        return ResponseEntity.ok(login);
    }
}
