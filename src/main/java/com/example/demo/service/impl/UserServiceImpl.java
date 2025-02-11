package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.dao.model.User;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;



public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(String username, String password, String email, String phone, String role) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        try {
            boolean isRegistered = userDao.registerUser(username, hashedPassword, email, phone, role);
            if (isRegistered) {
                logger.info("User successfully registered with username: {}", username);
            } else {
                logger.warn("User registration failed for username: {}", username);
            }
            return isRegistered;
        } catch (RuntimeException e) {
            logger.error("Unexpected error during registration for username: {}", username, e);
            return false;
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            String hashedPassword = userDao.getHashedPassword(username);
            if (hashedPassword == null) {
                logger.warn("User with username '{}' not found", username);
                return false;
            }

            logger.debug("Fetched hashed password for user '{}': {}", username, hashedPassword);

            boolean isPasswordCorrect = BCrypt.checkpw(password, hashedPassword);
            if (isPasswordCorrect) {
                logger.info("Authentication successful for user: {}", username);
            } else {
                logger.warn("Password mismatch for username: {}", username);
            }
            return isPasswordCorrect;
        } catch (Exception e) {
            logger.error("Error during authentication for user '{}'", username, e);
            return false;
        }
    }


    @Override
    public User getUserByUsername(String username) {
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                logger.info("User found with username: {}", username);
            } else {
                logger.warn("User not found with username: {}", username);
            }
            return user;
        } catch (RuntimeException e) {  // Обрабатываем RuntimeException
            logger.error("Unexpected error retrieving user with username: {}", username, e);
            return null;
        }
    }
}