package com.AB.bookServer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.JwtUtil.JwtUtil;
import com.AB.bookServer.model.AuthRequest;
import com.AB.bookServer.model.User;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userOperation;

	@Autowired
	private JwtUtil jwt;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome your security implemented successfully";
	}

	@PostMapping("/login")
	public Response generateToken(@RequestBody AuthRequest user,HttpServletResponse response) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username or password");
		}
		String token = jwt.generateToken(user.getEmail());
		Cookie cookie = new Cookie("jwtToken", token);
		response.addCookie(cookie);
		return new Response(true, "You have logged in successfully with emailId : "+user.getEmail() ,token);
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
	public ResponseEntity<?> getUserDetails() {
		return ResponseEntity.ok(userOperation.getUser());
	}
}

// method to add or send cookie
//public String setCookie(HttpServletResponse response) {
//    Cookie cookie = new Cookie("aman", "kumar");
//    response.addCookie(cookie);
//    return "cookies sent";
//}

// to read single cookie
//@GetMapping("/")
//public String readCookie(@CookieValue(value = "username", defaultValue = "aman") String username) {
//    return "My username is " + username;
//}

// method to get all cookies
//public String readAllCookies(HttpServletRequest request) {
//    Cookie[] cookies = request.getCookies();
//    if (cookies != null) {
//        return Arrays.stream(cookies)
//                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
//    }
//    return "No cookies";
//}

















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



