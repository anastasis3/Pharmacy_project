package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/*")
public class LanguageFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LanguageFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String lang = httpRequest.getParameter("lang");

        if (lang != null && !lang.equals(session.getAttribute("lang"))) {
            logger.debug("Setting language to: {}", lang);
            session.setAttribute("lang", lang);
        }

        chain.doFilter(request, response);
    }
}
