package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.NetBankingRequest;
import com.project.Banking.Application.dto.NetBankingResponse;
import com.project.Banking.Application.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    String getAccountHolder(String accountNumber);

    List<User> getAllUsers();
    User getUserByAccountNumber(String accountNumber);

    NetBankingResponse saveCredentials(String accountNumber, NetBankingRequest bankingRequest);
}
