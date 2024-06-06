package com.example.dulevich.todolist.mapper;

import com.example.dulevich.todolist.dto.CreateTaskDto;
import com.example.dulevich.todolist.entity.Status;
import com.example.dulevich.todolist.entity.Task;
import com.example.dulevich.todolist.utils.LocalDateTimeFormatter;

import java.time.LocalDateTime;

public class CreateTaskMapper implements Mapper<CreateTaskDto, Task>{

    public static final CreateTaskMapper INSTANCE  = new CreateTaskMapper();

    public static CreateTaskMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public Task mapFrom(CreateTaskDto object) {
        return Task.builder()
                .taskName(object.getTaskName())
                .status(Status.valueOf(object.getStatus()))
                .deadline(LocalDateTimeFormatter.format(object.getDeadLine()))
                .build();
    }
}
