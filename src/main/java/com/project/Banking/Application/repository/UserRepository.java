package com.project.Banking.Application.repository;

import com.project.Banking.Application.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByName(String name);
}
