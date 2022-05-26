package com.AB.bookServer.servicesMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AB.bookServer.model.Book;
import com.AB.bookServer.repository.BookRepository;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.BookService;

@Service
public class BookServiceMethod implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public Map<String, Object> getAllBookInPage(int pageNo, int pageSize, String sortBy) {
		Map<String, Object> response = new HashMap<String, Object>();
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Book> bookPage = bookRepo.findAll(page);
		response.put("data", bookPage.getContent());
		response.put("Total No Of page", bookPage.getTotalPages());
		response.put("Total no of Elements", bookPage.getTotalElements());
		response.put("Current Page No.", bookPage.getNumber());
		return response;
	}
	@Override
	public  List<Book> getBooksBySearch(String val){
		     List<Book> books0 = bookRepo.findByQueryInCategory(val);
		
		     List<Book> books1 =bookRepo.findByQueryInauthor(val);
			
		     List<Book> books2 = bookRepo.findByQueryInTitle(val);	
		     books0.addAll(books1);
		     System.out.println(books0);
		     books0.addAll(books2);
		     return books0;
	}
	
	@Override
	public Response saveBook(Book book) {
		try {
			book.setAddedOn(new Date(System.currentTimeMillis()));
			Book bookData = bookRepo.save(book);
			if (!bookData.toString().isEmpty()) {
				Response data = new Response(true, "Success", bookData);
				return data;
			} else {
				Response data = new Response(false, "Some error occured");
				return data;
			}
		} catch (Exception e) {
			Response data = new Response(false, e.getMessage());
			return data;
		}
	}

	@Override
	public Response getBooks() {
//		List<Book> output = bookRepo.findAll();
		List<Book> output = bookRepo.findIsDeleted();
		if (!output.isEmpty()) {
			Response data = new Response(true, "success", output);
			return data;
		}
		Response data = new Response(false, "failed");
		return data;
	}

	@Override
	public Response updateBook(ObjectId id, Book bookData) {
		Optional<Book> findBook = bookRepo.findById(id);
		if (findBook.isPresent()) {
			Book bookToSave = findBook.get();
			bookToSave.setUpdatedOn(new Date(System.currentTimeMillis()));
			bookToSave.setTitle(bookToSave.getTitle() != null ? bookData.getTitle() : bookToSave.getTitle());
			bookToSave.setAuthor(bookToSave.getAuthor() != null ? bookData.getAuthor() : bookToSave.getAuthor());
			bookToSave.setPages(bookToSave.getPages() <= 0 ? bookData.getPages() : bookToSave.getPages());
			bookToSave.setPrice(bookToSave.getPrice() <= 0 ? bookData.getPrice() : bookToSave.getPrice());
			bookToSave.setPublishDate(
					bookToSave.getPublishDate() != null ? bookData.getPublishDate() : bookToSave.getPublishDate());
			bookToSave
					.setCategory(bookToSave.getCategory() != null ? bookData.getCategory() : bookToSave.getCategory());
			bookToSave
					.setImageUrl(bookToSave.getImageUrl() != null ? bookData.getImageUrl() : bookToSave.getImageUrl());
			bookRepo.save(bookToSave);
		}
		Optional<Book> findContactResponse = bookRepo.findById(id);
		Response data = new Response(true, "Success", findContactResponse.get());
		return data;
	}

	@Override
	public Response deleteBook(ObjectId id) {
		Optional<Book> findBook = bookRepo.findById(id);
		if (findBook.isPresent()) {
			Book bookDeleted = findBook.get();
			bookDeleted.setIsDeleted(true);
			bookRepo.save(bookDeleted);
			Response data = new Response(true, "Book deleted successfully");
			return data;
		}
		Response data = new Response(false, "Could not be deleted");
		return data;
	}

}
