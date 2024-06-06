package com.example.dulevich.todolist.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@UtilityClass
public class ConnectionManager {
    public static final String URL_KEY = "spring.datasource.url";
    public static final String USERNAME_KEY = "spring.datasource.username";
    public static final String PASSWORD_KEY = "spring.datasource.password";
    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows // не юзать
    public static Connection get() {
        return DriverManager.getConnection(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USERNAME_KEY),
                PropertiesUtil.get(PASSWORD_KEY));

    }

}