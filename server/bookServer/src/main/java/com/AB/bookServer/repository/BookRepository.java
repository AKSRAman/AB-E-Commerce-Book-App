package com.AB.bookServer.repository;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.AB.bookServer.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book,ObjectId> {
	
 
}
