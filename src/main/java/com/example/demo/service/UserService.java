package com.example.demo.service;

import com.example.demo.dao.model.User;

public interface UserService {
    boolean registerUser(String username, String password, String email, String phone, String role);
    boolean authenticate(String username, String password);
    User getUserByUsername(String username);

}