package com.example.demo.controller.command;

import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteOrderCommand implements BaseCommand {

    private static final Logger logger = LogManager.getLogger(DeleteOrderCommand.class);
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String SUCCESS_MESSAGE = "successMessage";
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");

        if (!"admin".equals(role)) {
            logger.warn("Unauthorized delete attempt by role: {}", role);
            request.setAttribute(ERROR_MESSAGE, "You do not have permission to delete orders.");
            return "/jsp/orderHistory.jsp";
        }

        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam == null || orderIdParam.isEmpty()) {
            logger.error("Order ID not provided or is empty.");
            request.setAttribute(ERROR_MESSAGE, "Order ID is missing.");
            return "/jsp/orderHistory.jsp";
        }

        try {
            int orderId = Integer.parseInt(orderIdParam);
            boolean isDeleted = orderService.deleteOrder(orderId);

            if (isDeleted) {
                request.setAttribute(SUCCESS_MESSAGE, "Order with ID " + orderId + " was successfully deleted.");
                logger.info("Order with ID {} successfully deleted by user with role {}", orderId, role);
            } else {
                request.setAttribute(ERROR_MESSAGE, "Order with ID " + orderId + " not found.");
                logger.warn("Attempt to delete non-existent order with ID {}", orderId);
            }

        } catch (NumberFormatException e) {
            logger.error("Invalid order ID format: {}", orderIdParam, e);
            request.setAttribute(ERROR_MESSAGE, "Invalid order ID format.");
        } catch (RuntimeException e) {
            logger.error("Error deleting order with ID {}: {}", orderIdParam, e.getMessage(), e);
            request.setAttribute(ERROR_MESSAGE, "Error deleting order. Please try again later.");
        }

        return new ViewOrdersCommand().execute(request);
    }
}
