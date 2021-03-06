package com.AB.bookServer.controller;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.JwtUtil.JwtUtil;
import com.AB.bookServer.model.AuthRequest;
import com.AB.bookServer.model.Book;
import com.AB.bookServer.model.User;
import com.AB.bookServer.repository.UserRepository;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userOperation;

	@Autowired
	private JwtUtil jwt;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/decodeToken")
	public ResponseEntity<?> decodeTokenjh(@RequestParam(value = "token") String token) {
		return ResponseEntity.ok(jwt.getAllClaimsFromToken(token));
	}

	@PostMapping("/")
	public ResponseEntity<?> addNewUser(@RequestBody User userData) {
		Response data = userOperation.saveUser(userData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUserDetails(@RequestHeader(value = "Authorization") String token,
			HttpServletResponse res) {
		System.out.println("I am coming from controller");
		System.out.println(token);
		System.out.println("Operation Successfull and Finally Closed");
		return ResponseEntity.ok(userOperation.getUser());
	}

	@GetMapping("/fetchUser")
	public ResponseEntity<?> getUserBbyId(@RequestHeader(value = "Authorization") String token) {
		Response data = userOperation.fetchUser(token);
		if (data.getStatus() == true) {
			return ResponseEntity.status(201).body(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@PostMapping("/loginWithOtp")
	public ResponseEntity<?> loginWithOtp(@RequestBody AuthRequest user) throws Exception {
		System.out.println(user.getEmail());
		Response output = userOperation.sendEmail(user.getEmail());
		if (output.getStatus() == true) {
			return ResponseEntity.ok(output);
		} else {
			return ResponseEntity.status(400).body(output);
		}
	}

	@PostMapping("/verifyOtp/{email}/{otp}")
	public ResponseEntity<?> verifyOtp(@PathVariable String email, @PathVariable String otp) throws Exception {
		Response output = userOperation.verifyOtp(email, otp);
		if (output.getStatus() == true) {
			User userFound = userRepo.findByEmail(email);
			String token = jwt.generateToken(userFound);
			return ResponseEntity
					.ok(new Response(true, "You have logged in successfully with emailId : " + email, token));
		}
		return ResponseEntity.status(400).body(new Response(false, "Invalid OTP", output.getUser()));
	}

	@PostMapping("/login")
	public Response login(@RequestBody AuthRequest user, HttpServletResponse response) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username or password");
		}
		User userFound = userRepo.findByEmail(user.getEmail());
		String token = jwt.generateToken(userFound);
		return new Response(true, "You have logged in successfully with emailId : " + user.getEmail(), token);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable ObjectId userId, @RequestBody User userData) {
		Response data = userOperation.updateUser(userId, userData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestHeader(value = "Authorization") String token, @RequestBody Book book) {
		Response data = userOperation.addInCart(token, book);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@DeleteMapping("/removeFromCart/{i}")
	public ResponseEntity<?> removeFromCart(@RequestHeader(value = "Authorization") String token, @PathVariable int i) {
		Response data = userOperation.removeFromCart(token, i);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

}
