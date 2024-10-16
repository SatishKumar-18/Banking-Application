package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.*;
import com.project.Banking.Application.entity.Account;
import com.project.Banking.Application.entity.Employee;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.repository.EmployeeRepository;
import com.project.Banking.Application.utils.AccountUtils;
import com.project.Banking.Application.utils.EmployeeUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public BankResponse createCustomerAccount(UserRequest userRequest) {
        /*
         * Creating an account - saving a new user into the database
         */

        try{
            User newUser = User.builder()
                    .name(userRequest.getName())
                    .dateOfBirth(userRequest.getDateOfBirth())
                    .gender(userRequest.getGender())
                    .address(userRequest.getAddress())
                    .city(userRequest.getCity())
                    .state(userRequest.getState())
                    .aadharNumber(userRequest.getAadharNumber())
                    .panNumber(userRequest.getPanNumber())
                    .email(userRequest.getEmail())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .build();

            Account newAccount = Account.builder()
                    .accountType("Saving")
                    .accountNumber(AccountUtils.generateAccountNumber())
                    .accountBalance(BigDecimal.ZERO)
                    .status("ACTIVE")
                    .createdAt(LocalDateTime.now())
                    .build();

            Account savedAccount = accountService.saveAccount(newAccount);

            newUser.setAccount(savedAccount);
            User savedUser = userService.saveUser(newUser);

            //sent an email
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(savedUser.getEmail())
                    .subject("Account Creation")
                    .messageBody("Congratulations! Your Account has been successfully created.\nYour Account Details\n" +
                            "Account Name:" + savedUser.getName() + "\nAccount Number: "+savedAccount.getAccountNumber())
                    .build();
            emailService.sendEmailAlert(emailDetails);

            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATED)
                    .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                    .accountsInfo(AccountsInfo.builder()
                            .accountName(savedUser.getName())
                            .accountNumber(savedAccount.getAccountNumber())
                            .accountBalance(savedAccount.getAccountBalance())
                            .build())
                    .build();

        } catch(Exception e){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountsInfo(null)
                    .build();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUser(String accountNumber){
        return userService.getUserByAccountNumber(accountNumber);
    }

    @Override
    public AdminResponse createEmployee(EmployeeRequest employeeRequest) {
       try{
           Employee employee = Employee.builder()
                   .name(employeeRequest.getName())
                   .username(employeeRequest.getUsername())
                   .password(employeeRequest.getPassword())
                   .position(employeeRequest.getPosition())
                   .build();

           Employee savedEmployee = employeeRepo.save(employee);

           return AdminResponse.builder()
                   .responseCode(EmployeeUtils.EMPLOYEE_CREATED_CODE)
                   .responseMessage(EmployeeUtils.EMPLOYEE_CREATED_MESSAGE)
                   .employeeInfo(EmployeeInfo.builder()
                           .employeeName(savedEmployee.getName())
                           .username(savedEmployee.getUsername())
                           .password(savedEmployee.getPassword())
                           .build())
                   .build();
       }catch (Exception e){
            return AdminResponse.builder()
                    .responseCode(EmployeeUtils.EMPLOYEE_CREATION_FAILED_CODE)
                    .responseMessage(EmployeeUtils.EMPLOYEE_CREATION_FAILED_MESSAGE)
                    .employeeInfo(null)
                    .build();
       }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(ObjectId id) {
        return employeeRepo.findById(id).orElse(null);
    }


}
