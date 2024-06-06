package com.example.dulevich.todolist.service;


import com.example.dulevich.todolist.dao.TaskDao;
import com.example.dulevich.todolist.dto.CreateTaskDto;
import com.example.dulevich.todolist.dto.TaskDto;
import com.example.dulevich.todolist.entity.Task;
import com.example.dulevich.todolist.mapper.CreateTaskMapper;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class TaskService {
    private static final TaskService INSTANCE = new TaskService();
    private static final TaskDao taskDao = TaskDao.getInstance();

    private static final CreateTaskMapper createTaskMapper = CreateTaskMapper.getInstance();


    public List<TaskDto> findAll() {
        return taskDao.findAll().stream().
                map(task -> TaskDto.builder().id((task.getId())).description(
                                """
                                        %s - %s - %s
                                        """.formatted(task.getStatus(),
                                        task.getDeadline(),
                                        task.getTaskName())).
                        build()
                )
                .collect(toList());
    }

    public Optional<TaskDto> findById(Long id) {
        Optional<Task> taskOptional = taskDao.findById(id);
        return taskOptional.map(task -> TaskDto.builder()
                .id(task.getId())
                .description(String.format("%s - %s - %s",
                        task.getTaskName(),
                        task.getStatus(),
                        task.getDeadline()))
                .build());

    }

    public static void save(CreateTaskDto taskDto) {
        var taskEntity = createTaskMapper.mapFrom(taskDto);
        taskDao.save(taskEntity);
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }
}