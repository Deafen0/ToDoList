package com.example.dulevich.todolist.mapper;


import com.example.dulevich.todolist.dto.CreateUserDto;
import com.example.dulevich.todolist.entity.Role;
import com.example.dulevich.todolist.entity.User;
import com.example.dulevich.todolist.utils.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    public static final CreateUserMapper INSTANCE  = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }
    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
