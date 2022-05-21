package com.AB.bookServer.servicesMethod;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<Book> output = bookRepo.findAll();
		if (!output.isEmpty()) {
			Response data = new Response(true, "success", output);
			return data;
		}
		Response data = new Response(false, "failed");
		return data;
	}

	@Override
	public Response updateBook(ObjectId id, Book inputContactData) {
		Optional<Book> findContact = bookRepo.findById(id);
		if (findContact.isPresent()) {
			Book contactUpdate = findContact.get();
//			if (inputContactData.getName() != null && !inputContactData.getName().isBlank()) {
//				contactUpdate.setName(inputContactData.getName());
//			}
//			if (inputContactData.getEmail() != null && !inputContactData.getEmail().isBlank()) {
//				contactUpdate.setEmail(inputContactData.getEmail());
//			}
//			if (inputContactData.getPlace() != null && !inputContactData.getPlace().isBlank()) {
//				contactUpdate.setPlace(inputContactData.getPlace());
//			}
			bookRepo.save(contactUpdate);
		}
		Optional<Book> findContactResponse = bookRepo.findById(id);
		Response data = new Response(true, "Success", findContactResponse.get());
		return data;
	}

	@Override
	public Response deleteBook(ObjectId id) {
		bookRepo.deleteById(id);
		Response data = new Response(true, "Contact deleted successfully");
		return data;
	}
}

//Creating GetApi EndPoint To get All books Data 

//@GetMapping("/books")
//public ResponseEntity<?> getAllBooks() {
//	List<Book> allBook = bookOperation.getBooks();
//	if (allBook.size() > 0) {
//		return new ResponseEntity<List<Book>>(allBook, HttpStatus.OK);
//	} else {
//		return new ResponseEntity<>("Nothing To Show ", HttpStatus.OK);
//	}
//}
//
////PostApi To Add New Book Data To Database
//
//@PostMapping("/books")
//public ResponseEntity<?> addContact(@RequestBody Book bookData) {
//	try {
//		bookData.setAddedOn(new Date(System.currentTimeMillis()));
//		bookRepo.save(bookData);
//		return new ResponseEntity<Book>(bookData, HttpStatus.OK);
//	} catch (Exception e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
//
//@PutMapping("/books/{id}")
//public ResponseEntity<?> updateContact(@PathVariable("id") ObjectId id, @RequestBody Book bookData) {
//	Optional<Book> bookToUpdate = bookRepo.findById(id);
//	if (bookToUpdate.isPresent()) {
//		Book bookToSave = bookToUpdate.get();
//		bookToSave.setUpdatedOn(new Date(System.currentTimeMillis()));
//		bookToSave.setTitle(bookToSave.getTitle() != null ? bookData.getTitle() : bookToSave.getTitle());
//		bookToSave.setAuthors(bookToSave.getAuthors() != null ? bookData.getAuthors() : bookToSave.getAuthors());
//		bookToSave.setPages(bookToSave.getPages() <= 0 ? bookData.getPages() : bookToSave.getPages());
//		bookToSave.setPrice(bookToSave.getPrice() <= 0 ? bookData.getPrice() : bookToSave.getPrice());
//		bookToSave.setPublishDate(
//				bookToSave.getPublishDate() != null ? bookData.getPublishDate() : bookToSave.getPublishDate());
//		bookToSave
//				.setCategory(bookToSave.getCategory() != null ? bookData.getCategory() : bookToSave.getCategory());
//		bookToSave.setRating(bookToSave.getRating() < 0 ? bookData.getRating() : bookToSave.getRating());
//		bookToSave
//				.setImageUrl(bookToSave.getImageUrl() != null ? bookData.getImageUrl() : bookToSave.getImageUrl());
//		return new ResponseEntity<Book>(bookToSave, HttpStatus.OK);
//	} else {
//		return new ResponseEntity<>("Contact not found with id " + id, HttpStatus.NOT_FOUND);
//	}
//}
//
//@DeleteMapping("/books/{id}")
//public ResponseEntity<?> deleteTodoById(@PathVariable("id") ObjectId id) {
//	try {
//		Optional<Book> bookToDelete = bookRepo.findById(id);
//		bookRepo.deleteById(id);
//		return new ResponseEntity<>(bookToDelete, HttpStatus.OK);
//	} catch (Exception e) {
//
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//	}
//}
//
//@PostMapping("/saveNewContact")
//public ResponseEntity<?> saveNewContact(@RequestBody ContactModel contact) {
//	Response output = contactOperation.saveContact(contact);
//	if(output.getStatus()==true) {
//		return ResponseEntity.ok(contactOperation.saveContact(contact));
//	} else {
//		return ResponseEntity.status(400).body(output);
//	}
//}
