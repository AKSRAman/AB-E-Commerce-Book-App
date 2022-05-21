package com.AB.bookServer.services;

import org.bson.types.ObjectId;
import com.AB.bookServer.model.User;
import com.AB.bookServer.response.Response;

public interface UserService {

	public Response saveUser(User contact);

	public Response getUser();

	public Response updateUser(ObjectId id, User inputContactData);

	public Response deleteUser(ObjectId id);
	
}
