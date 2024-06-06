package com.example.dulevich.todolist.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalDateTimeFormatter {
    public static final String PATTERN = "yyyy-MM-dd'T'HH:mm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime format(String date){
        Objects.requireNonNull(date, "text must not be null");
        return LocalDateTime.parse(date,FORMATTER);
    }
}
