package com.AB.bookServer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public ResponseEntity<?> decodeToken(@RequestParam(value="token") String token) {
		return ResponseEntity.ok(jwt.getAllClaimsFromToken(token));
	}
	
	@GetMapping("/getCookies")
	public ResponseEntity<?> getCookies(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getCookies());
//		System.out.println("amanKumarCookies");
		Cookie cookie = new Cookie("name", "aman");
		cookie.setHttpOnly(true);
		res.addCookie(cookie);
		//res.addHeader("jwtToken", "");
		return ResponseEntity.ok(req.getCookies());
	}
	
	@PostMapping("/addNewUser")
	public ResponseEntity<?> addNewUser(@RequestBody User userData) {
		Response data = userOperation.saveUser(userData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUserDetails(@RequestHeader(value="Authorization") String token, HttpServletResponse res) {
		System.out.println(token);
		System.out.println("amanKumarToken");
		Cookie cookie = new Cookie("jwttoken", "aman");
		cookie.setHttpOnly(true);
		res.addCookie(cookie);
		return ResponseEntity.ok(userOperation.getUser());
	}

	@PostMapping("/login")
	public Response generateToken(@RequestBody AuthRequest user, HttpServletResponse response) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username or password");
		}
		User userFound = userRepo.findByEmail(user.getEmail());
		String token = jwt.generateToken(userFound);
		Cookie cookie = new Cookie("jwtToken", token);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		response.addHeader("jwtToken", token);
		return new Response(true, "You have logged in successfully with emailId : " + user.getEmail(), token);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable ObjectId userId, @RequestBody User userData) {
		Response data = userOperation.updateUser(userId, userData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}
	
	@PostMapping("/addToCart/{userId}")
	public ResponseEntity<?> addToCart(@PathVariable ObjectId userId, @RequestBody Book book) {
		Response data = userOperation.addInCart(userId, book);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}
	
	@PutMapping("/removeFromCart/{userId}/{i}")
	public ResponseEntity<?> removeFromCart(@PathVariable ObjectId userId, @PathVariable int i) {
		Response data = userOperation.removeFromCart(userId, i);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

}
