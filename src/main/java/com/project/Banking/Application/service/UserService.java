package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
