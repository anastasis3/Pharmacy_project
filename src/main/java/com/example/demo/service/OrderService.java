package com.example.demo.service;

import com.example.demo.dao.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    boolean deleteOrder(int orderId);
    boolean updateOrder(Order order);
    Order getOrderById(int orderId);
}