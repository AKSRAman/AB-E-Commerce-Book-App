package com.AB.bookServer.servicesMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AB.bookServer.JwtUtil.JwtUtil;
import com.AB.bookServer.model.Book;
import com.AB.bookServer.model.User;
import com.AB.bookServer.repository.UserRepository;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.UserService;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceMethod implements UserService {

	@Autowired
	private UserRepository userRepo;

	
	@Autowired
	private JwtUtil jwt;
	
	
	public Claims jwtTokenDecoder(String token) {
		if(token.startsWith("Bearer ")) {
			String jwtToken = token.substring(7);
			return jwt.getAllClaimsFromToken(jwtToken);
		}
		return jwt.getAllClaimsFromToken(token);
	}
	
	@Override
	public Response fetchUser(String token) {
		Claims decodeToken = this.jwtTokenDecoder(token);
		String userId = (String) decodeToken.get("userId");
		ObjectId objectId = new ObjectId(userId);
		Optional<User> userFind = userRepo.findById(objectId);
		if (!decodeToken.isEmpty()) {
			Response data = new Response(true, "success", userFind.get());
			return data;
		}
		Response data = new Response(false, "failed");
		return data;
	}
	
	@Override
	public Response saveUser(User user) {
		user.setBooksCart(new ArrayList<Book>());
		user.setAddedOn(new Date(System.currentTimeMillis()));
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
	public Response updateUser(ObjectId id, User inputUserData) {
		Optional<User> findUser = userRepo.findById(id);
		if (findUser.isPresent()) {
			User userToSave = findUser.get();
			userToSave.setUpdatedOn(new Date(System.currentTimeMillis()));
			userToSave.setFullName(inputUserData.getfullName() != null ? inputUserData.getfullName() : userToSave.getfullName());
			userToSave.setEmail(inputUserData.getEmail() != null ? inputUserData.getEmail() : userToSave.getEmail());
			userToSave.setPassword(inputUserData.getPassword() != null ? inputUserData.getPassword() : userToSave.getPassword());
			userToSave.setAddress(inputUserData.getAddress() != null ? inputUserData.getAddress() : userToSave.getAddress());
			userToSave.setMobNumber(inputUserData.getMobNumber() > 999999999 ? inputUserData.getMobNumber() : userToSave.getMobNumber());
			userToSave.setProfilePic(inputUserData.getProfilePic() != null ? inputUserData.getProfilePic() : userToSave.getProfilePic());
			userRepo.save(userToSave);
		}
		Optional<User> findUserResponse = userRepo.findById(id);
		Response data = new Response(true, "Success", findUserResponse.get());
		return data;
	}
	
	@Override
	public Response addInCart(String token,Book book) {
		Claims decodeToken = this.jwtTokenDecoder(token);
		String userId = (String) decodeToken.get("userId");
		ObjectId objectId = new ObjectId(userId);
		Optional<User> findUser = userRepo.findById(objectId);
		if (findUser.isPresent()) {
			User userToSave = findUser.get();
			userToSave.getBooksCart().add(book);
			userRepo.save(userToSave);
			Optional<User> findUserResponse = userRepo.findById(objectId);
			Response data = new Response(true, "Success", findUserResponse.get());
			return data;
		}
		Response data = new Response(false, "failed");
		return data;
	}
	
	@Override
	public Response removeFromCart(String token,int i) {
		Claims decodeToken = this.jwtTokenDecoder(token);
		String userId = (String) decodeToken.get("userId");
		ObjectId objectId = new ObjectId(userId);
		Optional<User> findUser = userRepo.findById(objectId);
		if (findUser.isPresent()) {
			User userToSave = findUser.get();
			userToSave.getBooksCart().remove(i);
			userRepo.save(userToSave);
			Optional<User> findUserResponse = userRepo.findById(objectId);
			Response data = new Response(true, "Success", findUserResponse.get());
			return data;
		}
		Response data = new Response(false,"failed");
		return data;
	}
	
}
