package com.project.Banking.Application.controller;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.NetBankingRequest;
import com.project.Banking.Application.dto.NetBankingResponse;
import com.project.Banking.Application.service.AccountService;
import com.project.Banking.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/net-banking/create/account-number/{accountNumber}")
    public NetBankingResponse crateNetBanking(@PathVariable String accountNumber, @RequestBody NetBankingRequest bankingRequest){
        return userService.saveCredentials(accountNumber, bankingRequest);
    }

    @GetMapping("/account-balance/{accountNumber}")
    public BankResponse getBalance(@PathVariable String accountNumber){
        return accountService.balanceEnquiry(accountNumber);
    }
}
