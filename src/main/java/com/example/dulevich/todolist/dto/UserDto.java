package com.example.dulevich.todolist.dto;

import com.example.dulevich.todolist.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;

}