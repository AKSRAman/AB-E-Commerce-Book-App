package com.AB.bookServer.controller;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.Book;

import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	private BookService bookOperation;

	@PostMapping("/books")
	public ResponseEntity<?> addNewBooks(@RequestBody Book book) {
		Response output = bookOperation.saveBook(book);
		if (output.getStatus() == true) {
			return ResponseEntity.ok(output);
		}
		return ResponseEntity.status(400).body(output);
	}

	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok(bookOperation.getBooks());
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<?> getSinglebook(@PathVariable("id") ObjectId id) {
		return ResponseEntity.ok(bookOperation.getSingleBook(id));
	}

	@PutMapping("/books/{id}")
	public Response updateBookById(@PathVariable ObjectId id, @RequestBody Book book) {
		return bookOperation.updateBook(id, book);
	}

	@DeleteMapping("/books/{id}")
	public Response deleteBookById(@PathVariable ObjectId id) {
		return bookOperation.deleteBook(id);
	}

	@GetMapping("/books/page")
	public Map<String, Object> getAllBookInPage(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
			@RequestParam(name = "pagesize", defaultValue = "8") int pageSize,
			@RequestParam(name = "sortby", defaultValue = "id") String sortBy) {
		return bookOperation.getAllBookInPage(pageNo, pageSize, sortBy);
	}

	@GetMapping("/books/search")
	public ResponseEntity<?> getBooksBySearch(@RequestParam(name = "val", defaultValue = "l") String val) {
		try {
			List<Book> books = bookOperation.getBooksBySearch(val);

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

}