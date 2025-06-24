package com.nsu.exam_portal.dtos;

import com.nsu.exam_portal.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Role role;
}
