package com.example.dulevich.todolist.mapper;

import com.example.dulevich.todolist.dto.UserDto;
import com.example.dulevich.todolist.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    public static final UserMapper INSTANCE = new UserMapper();
    // MapStruct
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }
    public static UserMapper getInstance(){
        return INSTANCE;
    }
}
