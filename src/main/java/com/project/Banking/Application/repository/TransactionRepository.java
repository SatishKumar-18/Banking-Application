package com.project.Banking.Application.repository;

import com.project.Banking.Application.entity.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {
}
