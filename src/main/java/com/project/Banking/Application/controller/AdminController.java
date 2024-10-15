package com.project.Banking.Application.controller;

import com.project.Banking.Application.dto.*;
import com.project.Banking.Application.entity.Employee;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.service.AccountService;
import com.project.Banking.Application.service.AdminService;
import com.project.Banking.Application.service.TransactionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/employee/create-employee")
    public AdminResponse createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return adminService.createEmployee(employeeRequest);
    }

    @GetMapping("/employee/get-all")
    public ResponseEntity<?> getAllEmployee(){
        List<Employee> allEmployee = adminService.getAllEmployee();

        if(!allEmployee.isEmpty()){
            return new ResponseEntity<>(allEmployee, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employee/get/id/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable ObjectId id){
        Employee employeeById = adminService.getEmployeeById(id);

        if(employeeById != null){
            return new ResponseEntity<>(employeeById, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-account")
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return adminService.createCustomerAccount(userRequest);
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

    @PostMapping("/transaction/{accountNumber}")
    public TransactionResponse transaction(@PathVariable String accountNumber, @RequestBody TransactionRequest transactionRequest){
        return transactionService.saveTransaction(accountNumber, transactionRequest);
    }
}
