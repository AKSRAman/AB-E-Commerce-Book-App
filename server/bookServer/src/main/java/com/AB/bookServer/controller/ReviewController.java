package com.AB.bookServer.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AB.bookServer.model.Review;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

	@Autowired
	private ReviewService reviewOperation;

	@PostMapping("/saveReview/{bookId}")
	public ResponseEntity<?> createReview(@PathVariable ObjectId bookId, @RequestBody Review reviewData,
			@RequestHeader(value = "Authorization") String token) {
		Response data = reviewOperation.saveReview(bookId, reviewData, token);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@GetMapping("/getReviews")
	public ResponseEntity<?> getAllReview() {
		return ResponseEntity.ok(reviewOperation.getAllReviews());
	}

	@GetMapping("/getReviews/{bookId}")
	public ResponseEntity<?> getAllSpecifiedReview(@PathVariable String bookId) {
		Response output = reviewOperation.getSpecificBookReviews(bookId);
		if (output.getStatus() == true) {
			return ResponseEntity.ok(output);
		}
		return ResponseEntity.status(400).body(output);
	}

	@PutMapping("/updateReview/{reviewId}")
	public ResponseEntity<?> updateReview(@PathVariable ObjectId reviewId, @RequestBody Review reviewData) {
		Response data = reviewOperation.updateReview(reviewId, reviewData);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

	@DeleteMapping("/deleteReview")
	public ResponseEntity<?> deleteReview(@PathVariable ObjectId reviewId) {
		Response data = reviewOperation.deleteReview(reviewId);
		if (data.getStatus() == true) {
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.status(400).body(data);
	}

}
