package com.example.demo.controller.command;

import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(RegisterCommand.class);
    private final UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (username == null || password == null || email == null || phone == null) {
            logger.warn("Missing data for registration: username={}, email={}, phone={}", username, email, phone);
            return "/jsp/register.jsp?error=missing_data";
        }
        if(userService.registerUser(username, password, email, phone, "client")) {
            request.getSession().setAttribute("username", username);
            return "/jsp/main.jsp";
        } else {
            logger.error("Error registering user {}", username);
            return "/jsp/register.jsp?error=registration_failed";
        }
    }
}
