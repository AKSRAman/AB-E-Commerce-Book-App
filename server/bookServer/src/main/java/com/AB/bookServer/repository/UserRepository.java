package com.AB.bookServer.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.AB.bookServer.model.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {

}
