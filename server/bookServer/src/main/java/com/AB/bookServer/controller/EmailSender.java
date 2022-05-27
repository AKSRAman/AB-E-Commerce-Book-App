package com.AB.bookServer.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailSender {

	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/simpleemail")
	@ResponseBody
	String home() {
		try {
			sendEmail();
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}

	private void sendEmail() throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo("amankumarsingh60820@gmail.com");
		helper.setText("How are you?");
		helper.setSubject("Hi");
		sender.send(message);
	}

	@GetMapping("/cookie")
	public String setCookie(HttpServletResponse response) {
		// create a cookie
		Cookie cookie = new Cookie("username", "Jovan");
		// add cookie to response
		response.addCookie(cookie);
		return "Username is changed!";
	}

}
