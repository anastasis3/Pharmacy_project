package com.example.demo.dao;

import com.example.demo.dao.model.User;

public interface UserDao {
    boolean registerUser(String username, String password, String email, String phone, String role);
    User getUserByUsername(String username);
    String getHashedPassword(String username);
}