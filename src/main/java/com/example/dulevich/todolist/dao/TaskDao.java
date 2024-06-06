package com.example.dulevich.todolist.dao;

import com.example.dulevich.todolist.entity.Status;
import com.example.dulevich.todolist.entity.Task;
import com.example.dulevich.todolist.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao implements Dao<Long, Task> {
    public static final TaskDao INSTANCE = new TaskDao();
    private static final String FIND_ALL = """
            SELECT *
            FROM tasks
            """;

    private static final String FIND_BY_ID = """
            SELECT id
            FROM postgres.tasks
            WHERE id = ?
            """;
    private static final String ADD_TASK = """
            INSERT INTO tasks (taskname, deadline, status)
            VALUES (?, ?, ?)
            """;
    private static final String DELETE_TASK = """
            DELETE FROM tasks WHERE id = ?
            """;

    private TaskDao() {

    }

    public static TaskDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Task> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL)) {

            var resultSet = prepareStatement.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next())
                tasks.add(buildTask(resultSet));
            return tasks;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Optional<Task> findById(Long id) {
        try {
            var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(resultSet.getObject("tasks", Task.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public boolean delete(Integer taskId) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_TASK)) {

            prepareStatement.setInt(1, taskId);

            int rowsAffected = prepareStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public Task save(Task entity) {
        try (var connection = ConnectionManager.get()) {
            var prepareStatement = connection.prepareStatement(ADD_TASK);
            prepareStatement.setString(1, entity.getTaskName());
            prepareStatement.setObject(2, entity.getDeadline());
            prepareStatement.setString(3, entity.getStatus().toString());
            prepareStatement.executeUpdate();
            System.out.println("Task added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }

        return entity;
    }


    private Task buildTask(ResultSet resultSet) throws SQLException {

        return new Task(resultSet.getObject("id", Integer.class),
                resultSet.getObject("taskName", String.class),
                resultSet.getObject("deadline", LocalDateTime.class),
                Status.valueOf(resultSet.getObject("status", String.class)));

    }
}