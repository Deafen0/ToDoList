package com.example.dulevich.todolist.dto;

import com.example.dulevich.todolist.entity.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateTaskDto {
    String taskName;
    String deadLine;
    String status;

}