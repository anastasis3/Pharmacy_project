package com.example.demo.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest request);

}
