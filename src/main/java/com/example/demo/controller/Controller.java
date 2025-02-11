package com.example.demo.controller;

import java.io.*;

import com.example.demo.controller.command.BaseCommand;
import com.example.demo.controller.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@WebServlet(name = "Servlet", value = "/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);


    public void init() {
        logger.info("Servlet initialized with message: ");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Received GET request: {} {}", request.getMethod(), request.getRequestURI());
        processRequest(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Received POST request: {} {}", request.getMethod(), request.getRequestURI());
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        BaseCommand command = CommandType.defineCommand(commandStr);

        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }


}

