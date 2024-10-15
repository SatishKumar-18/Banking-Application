package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.EmailDetails;
import com.project.Banking.Application.dto.NetBankingRequest;
import com.project.Banking.Application.dto.NetBankingResponse;
import com.project.Banking.Application.entity.NetBanking;
import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.repository.UserRepository;
import com.project.Banking.Application.utils.AccountUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private NetBankingService netBankingService;
    @Autowired
    private EmailService emailService;


    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public String getAccountHolder(String accountNumber){
        List<User> user = userRepo.findAll();
        Optional<String> name = user.stream().filter(x -> x.getAccount().getAccountNumber().equals(accountNumber)).map(User::getName).findFirst();

        return name.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByAccountNumber(String accountNumber) {
        List<User> user = userRepo.findAll();
        return user.stream().filter(x -> x.getAccount().getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
    }

    @Override
    public NetBankingResponse saveCredentials(String accountNUmber, NetBankingRequest bankingRequest) {
        User user = getUserByAccountNumber(accountNUmber);

        if(user == null){
            return NetBankingResponse.builder()
                    .responseCode(AccountUtils.USER_FOUND_CODE)
                    .responseMessage(AccountUtils.USER_NOT_FOUND_MESSAGE)
                    .build();
        }

        NetBanking netBanking = NetBanking.builder()
                .username(bankingRequest.getUsername())
                .password(bankingRequest.getPassword())
                .build();

        NetBanking savedBanking = netBankingService.saveCredentials(netBanking);

        user.setNetBanking(savedBanking);
        userRepo.save(user);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Net-Banking Registration")
                .messageBody("Thank You! for using our net-banking service.")
                .build();

        emailService.sendEmailAlert(emailDetails);

        return NetBankingResponse.builder()
                .responseCode(AccountUtils.NET_BANKING_CREATED_CODE)
                .responseMessage(AccountUtils.NET_BANKING_CREATED_MESSAGE)
                .build();
    }
}
