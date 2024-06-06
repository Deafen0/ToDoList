package com.example.dulevich.todolist.dto;

import com.example.dulevich.todolist.dao.TaskDao;
import com.example.dulevich.todolist.entity.Status;
import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
public class TaskDto {
    Integer id;
    String description;
}
