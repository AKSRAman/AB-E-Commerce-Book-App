package com.AB.bookServer.servicesMethod;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AB.bookServer.model.User;
import com.AB.bookServer.repository.UserRepository;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.UserService;

@Service
public class UserServiceMethod implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Response saveUser(User user) {
		User saveDoc = userRepo.save(user);
		if (!saveDoc.toString().isEmpty()) {
			Response data = new Response(true, "Success", saveDoc);
			return data;
		} else {
			Response data = new Response(false, "Some error occured");
			return data;
		}
	}

	@Override
	public Response getUser() {
		List<User> output = userRepo.findAll();
		if (!output.isEmpty()) {
			Response data = new Response(true, "success", output, null);
			return data;
		}
		Response data = new Response(false, "failed");
		return data;
	}

	@Override
	public Response updateUser(ObjectId id, User inputContactData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteUser(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

}
