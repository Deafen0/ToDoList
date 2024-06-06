package com.example.dulevich.todolist.mapper;

public interface Mapper<F,T> {
    T mapFrom(F object);
}
