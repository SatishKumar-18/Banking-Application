package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.AccountsInfo;
import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.dto.UserRequest;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.repository.UserRepository;
import com.project.Banking.Application.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        /*
        * Creating an account - saving a new user into the database
        */

        try{
            User newUser = User.builder()
                    .name(userRequest.getName())
                    .gender(userRequest.getGender())
                    .address(userRequest.getAddress())
                    .city(userRequest.getCity())
                    .state(userRequest.getState())
                    .aadharNumber(userRequest.getAadharNumber())
                    .panNumber(userRequest.getPanNumber())
                    .email(userRequest.getEmail())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .accountNumber(AccountUtils.generateAccountNumber())
                    .accountBalance(BigDecimal.ZERO)
                    .status("ACTIVE")
                    .build();

            User savedUser = userRepo.save(newUser);

            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATED)
                    .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                    .accountsInfo(AccountsInfo.builder()
                            .accountName(savedUser.getName())
                            .accountNumber(savedUser.getAccountNumber())
                            .accountBalance(savedUser.getAccountBalance())
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
}
