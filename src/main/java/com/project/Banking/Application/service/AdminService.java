package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.AdminResponse;
import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.EmployeeRequest;
import com.project.Banking.Application.dto.UserRequest;
import com.project.Banking.Application.entity.Employee;
import com.project.Banking.Application.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface AdminService {
    BankResponse createCustomerAccount(UserRequest userRequest);
    List<User> getAllUsers();
    User getUser(String accountNumber);

    AdminResponse createEmployee(EmployeeRequest employeeRequest);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(ObjectId id);
}
