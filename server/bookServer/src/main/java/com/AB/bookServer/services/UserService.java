package com.AB.bookServer.services;

import org.bson.types.ObjectId;

import com.AB.bookServer.model.Book;
import com.AB.bookServer.model.User;
import com.AB.bookServer.response.Response;

public interface UserService {

	public Response saveUser(User contact);

	public Response getUser();

	public Response fetchUser(String token);

	public Response updateUser(ObjectId id, User inputContactData);

	public Response addInCart(String token, Book book);

	public Response removeFromCart(String token, int i);
	
	public Response sendEmail(String email);

	public Response verifyOtp(String email, String otp);
	
}
