package com.example.dulevich.todolist.servlet;


import com.example.dulevich.todolist.dto.UserDto;
import com.example.dulevich.todolist.service.UserService;
import com.example.dulevich.todolist.utils.JspHelper;
import com.example.dulevich.todolist.utils.UrlPath;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"),req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user,req,resp),
                        () -> onLoginFail(req,resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user,HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("user",user);
//        HttpSession session = req.getSession();
//        session.setAttribute("userId", user.getId());
        resp.sendRedirect("/createTask");
    }
}