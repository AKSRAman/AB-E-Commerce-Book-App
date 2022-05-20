package com.AB.bookServer.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.Book;
import com.AB.bookServer.repository.BookRepository;

@RestController
public class BookController {
	@Autowired
	public BookRepository bookRepo;

//Creating GetApi EndPoint To get All books Data 

	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		List<Book> allBook = bookRepo.findAll();
		if (allBook.size() > 0) {
			return new ResponseEntity<List<Book>>(allBook, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Nothing To Show ", HttpStatus.OK);
		}
	}

//	PostApi To Add New Book Data To Database

	@PostMapping("/books")
	public ResponseEntity<?> addContact(@RequestBody Book bookData) {
		try {
			bookData.setAddedOn(new Date(System.currentTimeMillis()));
			bookRepo.save(bookData);
			return new ResponseEntity<Book>(bookData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateContact(@PathVariable("id") ObjectId id, @RequestBody Book bookData) {
		Optional<Book> bookToUpdate = bookRepo.findById(id);
		if (bookToUpdate.isPresent()) {
			Book bookToSave = bookToUpdate.get();
			bookToSave.setUpdatedOn(new Date(System.currentTimeMillis()));
			bookToSave.setTitle(bookToSave.getTitle() != null ? bookData.getTitle() : bookToSave.getTitle());
			bookToSave.setAuthors(bookToSave.getAuthors() != null ? bookData.getAuthors() : bookToSave.getAuthors());
			bookToSave.setPages(bookToSave.getPages() <= 0 ? bookData.getPages() : bookToSave.getPages());
			bookToSave.setPrice(bookToSave.getPrice() <= 0 ? bookData.getPrice() : bookToSave.getPrice());
			bookToSave.setPublishDate(
					bookToSave.getPublishDate() != null ? bookData.getPublishDate() : bookToSave.getPublishDate());
			bookToSave
					.setCategory(bookToSave.getCategory() != null ? bookData.getCategory() : bookToSave.getCategory());
			bookToSave.setRating(bookToSave.getRating() < 0 ? bookData.getRating() : bookToSave.getRating());
			bookToSave
					.setImageUrl(bookToSave.getImageUrl() != null ? bookData.getImageUrl() : bookToSave.getImageUrl());
			return new ResponseEntity<Book>(bookToSave, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contact not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable("id") ObjectId id) {
		try {
			Optional<Book> bookToDelete = bookRepo.findById(id);
			bookRepo.deleteById(id);
			return new ResponseEntity<>(bookToDelete, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
