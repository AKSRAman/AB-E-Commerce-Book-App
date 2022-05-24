package com.AB.bookServer.response;

import java.util.List;

import com.AB.bookServer.model.Book;
import com.AB.bookServer.model.Review;
import com.AB.bookServer.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

	private Boolean status;
	private String message;
	private Book book;
	private User user;
	private Review review;
	private List<Book> bookList;
	private List<User> userList;
	private List<Review> reviewList;
	private String token;
	
	public Response(Boolean status, String message) {
		this.setStatus(status);
		this.setMessage(message);
	}
	
	public Response(Boolean status, String message, Book book) {
		this.setStatus(status);
		this.setMessage(message);
		this.setBook(book);
	}
	
	public Response(Boolean status, String message, User user) {
		this.setStatus(status);
		this.setMessage(message);
		this.setUser(user);
	}
	
	public Response(Boolean status, String message, Review review) {
		this.setStatus(status);
		this.setMessage(message);
		this.setReview(review);
	}
	
	public Response(Boolean status, String message, List<Book> bookList) {
		this.setStatus(status);
		this.setMessage(message);
		this.setBookList(bookList);
	}
	
	public Response(Boolean status, String message, List<User> userList, Boolean check) {
		this.setStatus(status);
		this.setMessage(message);
		this.setUserList(userList);
	}
	
	public Response(Boolean status, String message,Boolean check, List<Review> reviewList ) {
		this.setStatus(status);
		this.setMessage(message);
		this.setReviewList(reviewList);
	}
	
	public Response(Boolean status, String message, String token) {
		this.setStatus(status);
		this.setMessage(message);
		this.setToken(token);
	}
	
//	public Response(Boolean status, String message, List<User> userList) {
//		this.setStatus(status);
//		this.setMessage(message);
//		this.setUserList(userList);
//	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
	
}
