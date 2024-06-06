package com.example.dulevich.todolist.dto;

import com.example.dulevich.todolist.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;

}