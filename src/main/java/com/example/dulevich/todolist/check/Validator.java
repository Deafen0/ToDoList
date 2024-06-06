package com.example.dulevich.todolist.check;

public interface Validator<T> {

    ValidationResult isValid(T object);
}