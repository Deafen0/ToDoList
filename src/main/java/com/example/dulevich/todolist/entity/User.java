package com.example.dulevich.todolist.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private Role role;
}