package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.entity.Account;

public interface AccountService {

    Account saveAccount(Account account);
    Account getAccount(String accountNumber);
    BankResponse balanceEnquiry(String accountNumber);
}
