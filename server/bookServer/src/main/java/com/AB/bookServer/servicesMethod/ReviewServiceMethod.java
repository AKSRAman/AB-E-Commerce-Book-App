package com.AB.bookServer.servicesMethod;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AB.bookServer.model.Book;
import com.AB.bookServer.model.Review;
import com.AB.bookServer.repository.BookRepository;
import com.AB.bookServer.repository.ReviewRepository;
import com.AB.bookServer.response.Response;
import com.AB.bookServer.services.ReviewService;

@Service
public class ReviewServiceMethod implements ReviewService {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Override
	public Response saveReview(ObjectId bookid,Review review) {
		try {
		    review.setBookId(bookid.toString());
			review.setReviewedAt(new Date(System.currentTimeMillis()));
			Review reviewData = reviewRepo.save(review);
			if (!reviewData.toString().isEmpty()) {
				Optional<Book> findBook = bookRepo.findById(bookid);
				if (findBook.isPresent()) {
					Book bookToSave = findBook.get();
					bookToSave.setReviews(bookToSave.getReviews()+1);
					bookRepo.save(bookToSave);
				}
				Response data = new Response(true, "Success", reviewData);
				return data;
			} else {
				Response data = new Response(false, "Some error occured");
				return data;
			}
		} catch (Exception e) {
			Response data = new Response(false, e.getMessage());
			return data;
		}
	}

	@Override
	public Response getSpecificBookReviews(ObjectId bookid) {
		List<Review> reviewList = reviewRepo.findByBookId(bookid);
		if (!reviewList.isEmpty()) {
			Response data = new Response(true, "success", null, reviewList);
			return data;
		}
		Response data = new Response(false, "No book available");
		return data;
	}

	@Override
	public Response getAllReviews() {
		List<Review> reviewList = reviewRepo.findAll();
		if (!reviewList.isEmpty()) {
			Response data = new Response(true, "success", null, reviewList);
			return data;
		}
		Response data = new Response(false, "No book available");
		return data;
	}

	@Override
	public Response updateReview(ObjectId reviewId, Review inputReviewData) {
		Optional<Review> findReview = reviewRepo.findById(reviewId);
		if (findReview.isPresent()) {
			Review reviewToSave = findReview.get();
			reviewToSave.setUpdatedOn(new Date(System.currentTimeMillis()));
			reviewToSave.setReview(reviewToSave.getReview() != null ? inputReviewData.getReview() : reviewToSave.getReview());
			reviewToSave.setRating(reviewToSave.getRating() <= 0 ? inputReviewData.getRating() : reviewToSave.getRating());
			reviewRepo.save(reviewToSave);
		}
		Optional<Review> findReviewResponse = reviewRepo.findById(reviewId);
		Response data = new Response(true, "Success", findReviewResponse.get());
		return data;
	}
	
	@Override
	public Response deleteReview(ObjectId reviewId) {
		Optional<Review> findReview = reviewRepo.findById(reviewId);
		if (findReview.isPresent()) {
			Review reviewUpdate = findReview.get();
			reviewUpdate.setIsDeleted(true);
			reviewRepo.save(reviewUpdate);
			Response data = new Response(true, "Review deleted successfully");
			return data;
		}
		Response data = new Response(false, "Could not be deleted");
		return data;
	}
}
