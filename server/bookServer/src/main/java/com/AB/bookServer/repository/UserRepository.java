package com.AB.bookServer.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.AB.bookServer.model.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
	@Query("{email:?0}")
	 User findByEmail(String email);
}
