package com.project.Banking.Application.controller;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*@GetMapping("/account-balance/{username}")
    public ResponseEntity<BankResponse> getBalance(@PathVariable String username){
        User user = userService.findByUsername(username);
        if(user != null){
            String accountNumber = user.getAccountNumber();
            User accountHolder = userService.findByAccountNumber(accountNumber);

            BankResponse response = userService.balanceEnquiry(accountHolder);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
