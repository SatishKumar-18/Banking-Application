package com.project.Banking.Application.controller;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.UserRequest;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.service.AccountService;
import com.project.Banking.Application.service.AdminService;
import com.project.Banking.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create-account")
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return adminService.createAccount(userRequest);
    }

    @GetMapping("/account-balance/{accountNumber}")
    public BankResponse getCustomerBalance(@PathVariable String accountNumber){

        return accountService.balanceEnquiry(accountNumber);
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/get-user/{accountNumber}")
    public User getUser(@PathVariable String accountNumber){
        return adminService.getUser(accountNumber);
    }
}
