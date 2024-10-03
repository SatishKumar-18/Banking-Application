package com.project.Banking.Application.service;

import com.project.Banking.Application.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    String getAccountHolder(String accountNumber);

    List<User> getAllUsers();
    User getUser(String accountNumber);
}
