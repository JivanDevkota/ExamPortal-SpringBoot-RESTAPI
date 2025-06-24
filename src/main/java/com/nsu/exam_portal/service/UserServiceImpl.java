package com.nsu.exam_portal.service;

import com.nsu.exam_portal.dtos.AuthenticationRequest;
import com.nsu.exam_portal.dtos.SignupRequest;
import com.nsu.exam_portal.dtos.UserDto;
import com.nsu.exam_portal.model.Role;
import com.nsu.exam_portal.model.User;
import com.nsu.exam_portal.repository.RoleRepository;
import com.nsu.exam_portal.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void createTeacher() {
        // Check if role exists, otherwise create it
        Role role = roleRepository.findByRoleName("ROLE_TEACHER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_TEACHER");
                    return roleRepository.save(newRole);
                });

        // Check if a user with this role exists
        Optional<User> teacherOptional = userRepository.findByRoleName("ROLE_TEACHER");

        if (teacherOptional.isEmpty()) {
            User createUser = new User();
            createUser.setUserName("Jibs");
            createUser.setPassword(passwordEncoder.encode("1234"));
            createUser.setEmail("jibs@gmail.com");

            Set<Role> roles = new HashSet<>();
            roles.add(role); // use the persisted Role
            createUser.setRoles(roles);

            userRepository.save(createUser);
        }
    }

    @PostConstruct
    public void createStudent() {
        // Check if role exists, otherwise create it
        Role role = roleRepository.findByRoleName("ROLE_STUDENT")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_STUDENT");
                    return roleRepository.save(newRole);
                });

        // Check if a user with this role exists
        Optional<User> studentOptional = userRepository.findByRoleName("ROLE_STUDENT");

        if (studentOptional.isEmpty()) {
            User createUser = new User();
            createUser.setUserName("Jivan");
            createUser.setPassword(passwordEncoder.encode("1234"));
            createUser.setEmail("jivan@gmail.com");

            Set<Role> roles = new HashSet<>();
            roles.add(role); // use the persisted Role
            createUser.setRoles(roles);

            userRepository.save(createUser);
        }
    }


    public UserDto registerUser(SignupRequest request) {
        User user = modelMapper.map(request, User.class);
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        Role roleUser = roleRepository.findByRoleName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role Not Found"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(AuthenticationRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "Login done for user " + authenticate.getName();
        } catch (Exception e) {
            return "INVALID USERNAME or PASSWORD" + e.getMessage();
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

}
