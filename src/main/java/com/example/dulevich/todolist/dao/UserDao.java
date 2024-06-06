package com.example.dulevich.todolist.dao;

import com.example.dulevich.todolist.entity.Role;
import com.example.dulevich.todolist.entity.User;
import com.example.dulevich.todolist.utils.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, User> {
    public static final UserDao INSTANCE = new UserDao();
    public static final String SAVE_SQL =
            "INSERT INTO users (name, birthday, email, password, role) VALUES " + "(?,?,?,?,?)";
    private static final String EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String FIND_BY_ID = """
            SELECT * FROM tasks WHERE users_id = ?""";
    @SneakyThrows
    public Optional<User> findByLoginAndPassword(String email, String password){
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(EMAIL_AND_PASSWORD_SQL)) {
            prepareStatement.setString(1,email);
            prepareStatement.setString(2,password);
            var resultSet = prepareStatement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }


    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL,RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1, entity.getName());
            prepareStatement.setObject(2,entity.getBirthday());
            prepareStatement.setObject(3, entity.getEmail());
            prepareStatement.setObject(4, entity.getPassword());
            prepareStatement.setObject(5, entity.getRole().name());
            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
        return entity;
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name",String.class))
                .email(resultSet.getObject("email",String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }
}