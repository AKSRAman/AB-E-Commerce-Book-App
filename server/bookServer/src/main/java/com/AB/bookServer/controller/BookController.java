package com.AB.bookServer.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.Book;

import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	
	@Autowired
	private BookService bookOperation;
	
	@PostMapping("/addNewBooks")
	public ResponseEntity<?> getAllContac(@RequestBody Book book) {
		Response output = bookOperation.saveBook(book);
		if(output.getStatus()==true) {
			return ResponseEntity.ok(output);
		}
		return ResponseEntity.status(400).body(output);
	}
	
	@GetMapping("/getBooks")
	public ResponseEntity<?> getAllContact() {
		return ResponseEntity.ok(bookOperation.getBooks());
	}
	
	@PutMapping("/updateBook/{id}")
	public Response updateContactById(@PathVariable ObjectId id, @RequestBody Book book) {
		return bookOperation.updateBook(id,book);
	}

	@DeleteMapping("/deleteBook/{id}")
	public Response deleteContactById(@PathVariable ObjectId id) {
		return bookOperation.deleteBook(id);
	}
	
	
	
}
