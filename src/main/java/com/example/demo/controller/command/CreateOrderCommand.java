package com.example.demo.controller.command;

import com.example.demo.dao.model.Order;
import com.example.demo.dao.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class CreateOrderCommand implements BaseCommand {
    private final OrderService orderService = new OrderServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        String userName = (String) request.getSession().getAttribute("user_name");
        int medicineId = Integer.parseInt(request.getParameter("medicine"));
        String medicineName = getMedicineNameById(medicineId);
        String dosage = request.getParameter("dosage");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Order order = new Order(0, userId, medicineId, LocalDateTime.now(), quantity, Integer.parseInt(dosage), userName, medicineName);

        Order createdOrder = orderService.createOrder(order);


        if (createdOrder != null) {
            request.setAttribute("medicineName", createdOrder.getMedicineName());
            request.setAttribute("dosage", createdOrder.getDosage());
            request.setAttribute("quantity", createdOrder.getQuantity());
            request.setAttribute("orderedDate", createdOrder.getOrderDate());
            return "/jsp/orderConfirmation.jsp";
        } else {
            request.setAttribute("errorMessage", "Error creating order.");
            return "/error/500.jsp";
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