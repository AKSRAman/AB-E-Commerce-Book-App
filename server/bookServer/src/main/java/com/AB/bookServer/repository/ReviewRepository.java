package com.AB.bookServer.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.AB.bookServer.model.Review;

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
	@Query("{bookId:?0}")
	List<Review> findByBookId(ObjectId bookId);
}
