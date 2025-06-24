package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.AuthenticationRequest;
import com.nsu.exam_portal.dtos.AuthenticationResponse;
import com.nsu.exam_portal.jwt.JwtUtils;
import com.nsu.exam_portal.model.User;
import com.nsu.exam_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>generateToken(
            @RequestBody AuthenticationRequest request
            ){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtUtils.generateToken(authenticate.getName());
        String role = authenticate.getAuthorities().iterator().next().getAuthority();

        Optional<User> user = userRepository.findByUserName(request.getUsername());
        System.out.println(token);

        AuthenticationResponse response=new AuthenticationResponse(token,role,"successfull",user.get().getId());

        return ResponseEntity.ok(response);

    }
}
