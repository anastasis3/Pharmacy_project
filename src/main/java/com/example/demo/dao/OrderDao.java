package com.example.demo.dao;

import com.example.demo.dao.model.Order;
import java.util.List;

public interface OrderDao {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    boolean deleteOrder(int orderId);
    boolean updateOrder(Order order);
    Order getOrderById(int orderId);
}
