package com.example.demo.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements BaseCommand {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("user_name");
        if (username != null) {
            logger.info("User {} logged out successfully.", username);
        } else {
            logger.warn("Attempted logout with no user session.");
        }

        request.getSession().invalidate();
        return "/jsp/login.jsp";
    }
}
