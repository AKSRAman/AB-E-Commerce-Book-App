package com.AB.bookServer.services;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import com.AB.bookServer.model.Book;
import com.AB.bookServer.response.Response;


public interface BookService {

	public Response saveBook(Book contact);

	public Response getBooks();

	public Response updateBook(ObjectId id, Book inputContactData);

	public Response deleteBook(ObjectId id);
	
	public Map<String, Object> getAllBookInPage(int pageNo, int pageSize, String sortBy);

	public List<Book> getBooksBySearch(String val);

	public Object getSingleBook(ObjectId id);
	
}
