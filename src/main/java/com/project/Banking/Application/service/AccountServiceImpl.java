package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.AccountsInfo;
import com.project.Banking.Application.dto.BankResponse;
import com.project.Banking.Application.entity.Account;
import com.project.Banking.Application.repository.AccountRepository;
import com.project.Banking.Application.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserService userService;


    @Override
    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }

    @Override
    public BankResponse balanceEnquiry(String accountNumber) {
        Account account = getAccount(accountNumber);

        if(account != null){

            return BankResponse.builder()
                    .responseCode(AccountUtils.USER_FOUND_CODE)
                    .responseMessage(AccountUtils.USER_FOUND_MESSAGE)
                    .accountsInfo(AccountsInfo.builder()
                            .accountName(userService.getAccountHolder(accountNumber))
                            .accountBalance(account.getAccountBalance())
                            .accountNumber(account.getAccountNumber())
                            .build())
                    .build();
        }

        return BankResponse.builder()
                .responseCode(AccountUtils.USER_NOT_FOUND_CODE)
                .responseMessage(AccountUtils.USER_NOT_FOUND_MESSAGE)
                .build();
    }
}
