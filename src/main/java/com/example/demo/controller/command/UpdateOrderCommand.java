package com.example.demo.controller.command;

import com.example.demo.dao.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UpdateOrderCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger(UpdateOrderCommand.class);
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String method = request.getMethod();
        logger.info("Received request method: {}", method);

        if ("GET".equalsIgnoreCase(method)) {
            return handleGetRequest(request);
        } else if ("POST".equalsIgnoreCase(method)) {
            return handlePostRequest(request);
        }

        logger.error("Unsupported request method: {}", method);
        return "errorPage.jsp";
    }

    private String handleGetRequest(HttpServletRequest request) {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            logger.info("Fetching order with ID: {}", orderId);

            Order order = orderService.getOrderById(orderId);

            if (order != null) {
                request.setAttribute("order", order);
                logger.info("Order found: {}", order);
                return "/jsp/editOrder.jsp";
            } else {
                request.setAttribute("errorMessage", "Заказ не найден.");
                logger.warn("Order not found for ID: {}", orderId);
                return "/jsp/orderHistory.jsp";
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid orderId format", e);
            request.setAttribute("errorMessage", "Неверный формат ID заказа.");
            return "/jsp/orderHistory.jsp";
        }
    }

    private String handlePostRequest(HttpServletRequest request) {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int medicineId = Integer.parseInt(request.getParameter("medicine"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int dosage = Integer.parseInt(request.getParameter("dosage"));

            logger.info("Updating order ID: {} with medicineId: {}, quantity: {}, dosage: {}", orderId, medicineId, quantity, dosage); // Логируем данные для обновления

            String medicineName = getMedicineNameById(medicineId);
            Integer userId = (Integer) request.getSession().getAttribute("userId");

            Order updatedOrder = new Order(orderId, userId, medicineId, null, quantity, dosage, null, medicineName);
            boolean isUpdated = orderService.updateOrder(updatedOrder);

            if (isUpdated) {
                logger.info("Order ID: {} updated successfully.", orderId);
                request.setAttribute("successMessage", "Заказ успешно обновлён.");
                return "controller?command=viewOrders";
            } else {
                logger.error("Error updating order ID: {}", orderId);
                request.setAttribute("errorMessage", "Ошибка при обновлении заказа.");
                return "/jsp/editOrder.jsp";
            }
        } catch (NumberFormatException e) {
            logger.error("Error in input data: ", e);
            request.setAttribute("errorMessage", "Ошибка ввода данных.");
            return "/jsp/editOrder.jsp";
        }
    }

    private String getMedicineNameById(int medicineId) {
        return switch (medicineId) {
            case 1 -> "Парацетамол";
            case 2 -> "Амоксициллин";
            default -> "Неизвестный препарат";
        };
    }
}
