package com.project.Banking.Application.service;

import com.project.Banking.Application.entity.NetBanking;
import com.project.Banking.Application.repository.NetBankingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NetBankingServiceImpl implements NetBankingService{

    @Autowired
    private NetBankingRepository netBankingRepo;

    @Override
    public NetBanking saveCredentials(NetBanking netBanking) {
        return netBankingRepo.save(netBanking);
    }
}
