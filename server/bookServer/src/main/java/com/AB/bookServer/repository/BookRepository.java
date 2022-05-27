package com.AB.bookServer.repository;

import java.util.List;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.AB.bookServer.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, ObjectId> {

	@Query(value = "{'isDeleted':{$eq:false}}")
	List<Book> findIsDeleted();

	@Query("{ 'category': {$regex:?0   }})")
	List<Book> findByQueryInCategory(String val);

	@Query("{'author': {$regex: ?0  }})")
	List<Book> findByQueryInauthor(String val);

	@Query("{'title': {$regex: ?0  }})")
	List<Book> findByQueryInTitle(String val);

}
