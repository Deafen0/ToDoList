package com.example.dulevich.todolist.check;


import com.example.dulevich.todolist.dto.CreateUserDto;
import com.example.dulevich.todolist.entity.Role;
import com.example.dulevich.todolist.utils.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    public static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "BirthDay is invalid"));
        }

        if (Role.find(object.getRole()) == null) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
