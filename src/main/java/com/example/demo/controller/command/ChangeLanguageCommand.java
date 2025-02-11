package com.example.demo.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {

        return "/jsp/main.jsp";
    }
}
