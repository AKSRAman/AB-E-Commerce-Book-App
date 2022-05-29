package com.AB.bookServer.servicesMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	private JavaMailSender sender;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtUtil jwt;

	public Claims jwtTokenDecoder(String token) {
		if (token.startsWith("Bearer ")) {
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
		User emailCheck = userRepo.findByEmail(user.getEmail());
		if (emailCheck == null) {
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
		Response data = new Response(false, "Duplicate email");
		return data;
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
	public Response addInCart(String token, Book book) {
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
	public Response removeFromCart(String token, int i) {
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
		Response data = new Response(false, "failed");
		return data;
	}

	public String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public Response sendEmail(String email) {
		try {
			User user = userRepo.findByEmail(email);
			if (user != null) {
				emailSender(email);
				return new Response(true, "OTP Sent to " + email);
			}
			return new Response(false, "User details not found");
		} catch (Exception ex) {
			return new Response(false, ex.toString());
		}
	}

	private void emailSender(String email) throws Exception {
		User user = userRepo.findByEmail(email);
		String otpCode = getRandomNumberString();
		user.setOtp(otpCode);
		userRepo.save(user);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		helper.setSubject("Login OTP Verification for AB-Book-Store");
		helper.setText("Hii dear " + user.getfullName() + "," + " Your otp for login is : " + otpCode);
		sender.send(message);
	}

	public Response verifyOtp(String email, String otp) {
		try {
			User user = userRepo.findByEmail(email);
			if (user.getOtp().equals(otp)) {
				user.setOtp(null);
				userRepo.save(user);
				return new Response(true, "OTP verified successful");
			} else {
				return new Response(false, "Not authenticate");
			}
		} catch (Exception err) {
			return new Response(false, err.toString());
		}
	}
}
