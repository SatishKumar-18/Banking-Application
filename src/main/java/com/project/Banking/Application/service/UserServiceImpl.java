package com.project.Banking.Application.service;

import com.project.Banking.Application.entity.User;
import com.project.Banking.Application.repository.UserRepository;
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
    public User getUser(String accountNumber) {
        List<User> user = userRepo.findAll();
        return user.stream().filter(x -> x.getAccount().getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
    }


}
