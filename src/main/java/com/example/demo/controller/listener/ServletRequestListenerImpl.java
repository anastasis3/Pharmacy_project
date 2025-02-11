package com.example.demo.controller.listener;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;


@WebListener
public class ServletRequestListenerImpl implements ServletRequestListener {

    static Logger logger = LogManager.getLogger();

    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        String clientIp = request.getRemoteAddr();
        String requestURI = request instanceof HttpServletRequest ?
                ((HttpServletRequest) request).getRequestURI() : "N/A";

        logger.info("Request initialized at {} from IP: {} to URI: {}",
                LocalDateTime.now(), clientIp, requestURI);

    }

    public void requestDestroyed(ServletRequestEvent sre) {

        logger.info("Request destroyed");
    }
}
