package com.AB.bookServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.User;
import com.AB.bookServer.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/user/{email}")
	public ResponseEntity<?> getUserDetails() {
		return null;
	}

	@PostMapping("/user")
	public ResponseEntity<?> addNewUser(@RequestBody User userData) {
		try {
			userRepo.save(userData);
			return new ResponseEntity<User>(userData, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
