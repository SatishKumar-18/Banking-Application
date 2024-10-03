package com.project.Banking.Application.repository;

import com.project.Banking.Application.entity.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, ObjectId> {
    Account findByAccountNumber(String accountNumber);
}
