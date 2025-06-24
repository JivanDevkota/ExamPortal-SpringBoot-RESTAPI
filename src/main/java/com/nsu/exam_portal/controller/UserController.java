package com.nsu.exam_portal.controller;

import com.nsu.exam_portal.dtos.SignupRequest;
import com.nsu.exam_portal.dtos.UserDto;
import com.nsu.exam_portal.model.Category;
import com.nsu.exam_portal.service.CategoryService;
import com.nsu.exam_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/register")
    public ResponseEntity<?>registerUser(@RequestBody SignupRequest request){
        System.out.println("Registered for user1" +request.getUsername());
        UserDto userDto = userService.registerUser(request);
        System.out.println("Registered for user2" +request.getUsername());
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/teacher/create/category")
    public ResponseEntity<?>createCategory(@RequestBody Category category){
        Category category1 = categoryService.createCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @GetMapping("/teacher/all/category")
        public ResponseEntity<?> getAllCategories(){
            List<Category> allCategory = categoryService.getAllCategory();
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }
}
