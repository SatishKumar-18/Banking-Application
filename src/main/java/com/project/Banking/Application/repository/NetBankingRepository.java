package com.project.Banking.Application.repository;

import com.project.Banking.Application.entity.NetBanking;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NetBankingRepository extends MongoRepository<NetBanking, ObjectId> {
    NetBanking findByUsername(String username);
}
