package com.example.dulevich.todolist.check;


import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    String massage;
}