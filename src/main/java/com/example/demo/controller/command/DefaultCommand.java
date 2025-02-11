package com.example.demo.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) {
        return "";
    }
}
