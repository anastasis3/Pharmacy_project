package com.example.demo.dao.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.connection.ConnectionPool;
import com.example.demo.dao.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ERROR_CREATING_ORDER = "Error while creating order: ";
    private static final String ERROR_FETCHING_ORDERS = "Error while fetching orders: ";

    private static final String INSERT_ORDER_QUERY =
            "INSERT INTO orders (user_id, medicine_id, order_date, quantity, dosage) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ORDERS_QUERY =
            "SELECT o.id, o.user_id, o.medicine_id, o.order_date, o.quantity, o.dosage, " +
                    "m.name AS medicine_name, u.username AS user_name " +
                    "FROM orders o " +
                    "JOIN medicines m ON o.medicine_id = m.id " +
                    "JOIN users u ON o.user_id = u.user_id";

    @Override
    public Order createOrder(Order order) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getMedicineId());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(4, order.getQuantity());
            statement.setInt(5, order.getDosage());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        order.setId(generatedId);
                        return order;
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(ERROR_CREATING_ORDER, e);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orders.add(extractOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(ERROR_FETCHING_ORDERS, e);
        }
        return orders;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int medicineId = resultSet.getInt("medicine_id");
        LocalDateTime orderDate = resultSet.getObject("order_date", LocalDateTime.class);
        int quantity = resultSet.getInt("quantity");
        int dosage = resultSet.getInt("dosage");
        String medicineName = resultSet.getString("medicine_name");
        String userName = resultSet.getString("user_name");

        return new Order(orderId, userId, medicineId, orderDate, quantity, dosage, medicineName, userName);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        String deleteOrderQuery = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteOrderQuery)) {

            statement.setInt(1, orderId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Order with ID {} was successfully deleted.", orderId);
                return true;
            } else {
                logger.warn("Order with ID {} not found. No rows deleted.", orderId);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Database error while deleting order with ID {}: {}", orderId, e.getMessage(), e);
            throw new RuntimeException("Error deleting order with ID " + orderId, e);
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        String updateOrderQuery = "UPDATE orders SET medicine_id = ?, quantity = ?, dosage = ? WHERE id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateOrderQuery)) {

            statement.setInt(1, order.getMedicineId());
            statement.setInt(2, order.getQuantity());
            statement.setInt(3, order.getDosage());
            statement.setInt(4, order.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error updating order with ID {}: {}", order.getId(), e.getMessage(), e);
            return false;
        }
    }

    public Order getOrderById(int orderId) {
        String query = "SELECT o.id, o.user_id, o.medicine_id, o.quantity, o.dosage, o.order_date, " +
                "m.name AS medicine_name, u.username AS user_name " +
                "FROM orders o " +
                "JOIN medicines m ON o.medicine_id = m.id " +
                "JOIN users u ON o.user_id = u.user_id " +
                "WHERE o.id = ?;";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error fetching order by ID {}", orderId, e);
        }
        return null;
    }

}







