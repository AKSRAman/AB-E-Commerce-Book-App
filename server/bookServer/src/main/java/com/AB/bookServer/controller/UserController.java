package com.AB.bookServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.User;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userOperation;

	@PostMapping("/addNewUser")
	public ResponseEntity<?> addNewUser(@RequestBody User userData) {
		Response data = userOperation.saveUser(userData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUserDetails() {
		return ResponseEntity.ok(userOperation.getUser());
	}
}


















//@PostMapping("/addNewUser")
//public ResponseEntity<?> addNewUser(@RequestBody User userData) {
//	try {
//		userOperation.save(userData);
//		return new ResponseEntity<User>(userData, HttpStatus.OK);
//
//	} catch (Exception e) {
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}



