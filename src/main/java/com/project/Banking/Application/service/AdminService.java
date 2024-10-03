package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.UserRequest;
import com.project.Banking.Application.entity.User;

import java.util.List;

public interface AdminService {
    BankResponse createAccount(UserRequest userRequest);
    List<User> getAllUsers();
    User getUser(String accountNumber);
}
