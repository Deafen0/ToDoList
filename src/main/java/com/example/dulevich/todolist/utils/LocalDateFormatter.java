package com.example.dulevich.todolist.utils;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@UtilityClass
public class LocalDateFormatter {
    public static final String PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format (String date){
        Objects.requireNonNull(date, "text must not be null");
        return LocalDate.parse(date,FORMATTER);
    }

    public boolean isValid(String date){
        try {
            format(date);
            return true;
        } catch (DateTimeParseException exception){
            return false;
        }
    }

}
