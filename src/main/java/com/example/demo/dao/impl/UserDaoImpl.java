package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.connection.ConnectionPool;
import com.example.demo.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER_QUERY =
            "INSERT INTO users (username, password, email, phone, role, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_QUERY =
            "SELECT * FROM users WHERE username = ?";
    private static final String SELECT_PASSWORD_BY_USERNAME_QUERY =
            "SELECT password FROM users WHERE username = ?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean registerUser(String username, String password, String email, String phone, String role) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        LocalDateTime createdAt = LocalDateTime.now();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, role);
            statement.setObject(6, createdAt);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error while registering user with username: {}", username, e);
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_QUERY)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error while fetching user with username: {}", username, e);
        }
        return null;
    }

    @Override
    public String getHashedPassword(String username) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD_BY_USERNAME_QUERY)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            logger.error("Error while fetching hashed password for username: {}", username, e);
        }
        return null;
    }


    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSX");

        logger.debug("Extracting user from ResultSet...");

        User user = new User(
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("role"),
                parseDateTime(resultSet.getString("created_at"), formatter)
        );

        logger.debug("Extracted user: {}", user);
        return user;
    }


    private LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            logger.warn("Received null or empty date string for parsing");
            return null;
        }
        try {
            logger.debug("Parsing date string: {}", dateTimeStr);
            LocalDateTime dateTime = OffsetDateTime.parse(dateTimeStr, formatter).toLocalDateTime();
            logger.debug("Parsed LocalDateTime: {}", dateTime);
            return dateTime;
        } catch (DateTimeParseException e) {
            logger.error("Error parsing date: {}", dateTimeStr, e);
            return null;
        }
    }

}