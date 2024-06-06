package com.example.dulevich.todolist.service;


import com.example.dulevich.todolist.dao.UserDao;
import com.example.dulevich.todolist.dto.CreateUserDto;
import com.example.dulevich.todolist.dto.UserDto;
import com.example.dulevich.todolist.exceptions.ValidationException;
import com.example.dulevich.todolist.mapper.CreateUserMapper;
import com.example.dulevich.todolist.mapper.UserMapper;
import com.example.dulevich.todolist.check.CreateUserValidator;

import java.util.Optional;

public class UserService {
    public static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password){
        return userDao.findByLoginAndPassword(email,password)
                .map(userMapper::mapFrom);
    }
    public void create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
