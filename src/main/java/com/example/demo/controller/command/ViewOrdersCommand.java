package com.example.demo.controller.command;

import com.example.demo.dao.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ViewOrdersCommand implements BaseCommand {

    private static final Logger logger = LogManager.getLogger(ViewOrdersCommand.class);
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Fetching all orders from the database.");

        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            logger.warn("No orders found in the database.");
        } else {
            logger.info("Successfully retrieved {} orders.", orders.size());
        }

        request.setAttribute("orders", orders);
        return "/jsp/orderHistory.jsp";
    }
}
