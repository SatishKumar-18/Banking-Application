package com.project.Banking.Application.controller;

import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.UserRequest;
import com.project.Banking.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Working fine", HttpStatus.OK);
    }


}
