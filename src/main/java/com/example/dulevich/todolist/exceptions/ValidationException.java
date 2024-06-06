package com.example.dulevich.todolist.exceptions;

import com.example.dulevich.todolist.check.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<Error> errors;
    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
