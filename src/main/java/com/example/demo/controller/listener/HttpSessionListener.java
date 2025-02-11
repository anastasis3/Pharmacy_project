package com.example.demo.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@WebListener
public class HttpSessionListener implements HttpSessionAttributeListener {

    static Logger logger = LogManager.getLogger(HttpSessionListener.class);
    public void attributeAdded(HttpSessionBindingEvent event) {
        String login = (String) event.getSession().getAttribute("username");
        logger.info("User added to session: {}", login);
    }
}
