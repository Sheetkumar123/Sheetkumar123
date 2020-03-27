package com.training.eCommerce.dto;

import java.util.List;

public class ProductRatingsResponseDTO {
	
	private int statusCode;

	private String message;

	private List<RatingDTO> ratingsList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<RatingDTO> getRatingsList() {
		return ratingsList;
	}

	public void setRatingsList(List<RatingDTO> ratingsList) {
		this.ratingsList = ratingsList;
	}
	
}
