package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.impl.OrderDaoImpl;
import com.example.demo.dao.model.Order;
import com.example.demo.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Order createOrder(Order order) {
        return orderDao.createOrder(order); // Update to return the Order object
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
   public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }


}