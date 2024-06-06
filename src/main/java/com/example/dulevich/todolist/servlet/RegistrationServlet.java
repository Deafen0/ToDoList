package com.example.dulevich.todolist.servlet;


import com.example.dulevich.todolist.dto.CreateUserDto;
import com.example.dulevich.todolist.entity.Role;
import com.example.dulevich.todolist.service.UserService;
import com.example.dulevich.todolist.utils.JspHelper;
import com.example.dulevich.todolist.utils.UrlPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        // change on enums enumSet
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();

        userService.create(userDto);
        resp.sendRedirect("/login");

    }
}
