package com.AB.bookServer.services;

import org.bson.types.ObjectId;

import com.AB.bookServer.model.Review;
import com.AB.bookServer.response.Response;

public interface ReviewService {

	public Response saveReview(ObjectId bookid, Review review, String Token);

	public Response getSpecificBookReviews(String bookid);

	public Response getAllReviews();

	public Response updateReview(ObjectId id, Review inputReviewData);

	public Response deleteReview(ObjectId id);

}
