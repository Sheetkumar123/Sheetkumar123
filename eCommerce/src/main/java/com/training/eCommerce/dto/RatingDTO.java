package com.training.eCommerce.dto;

public class RatingDTO {

	private Integer ratingId;

	private Integer rating;

	private String Comment;

	private Integer customerId;

	private String customerName;

	private String ratedDate;

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRatedDate() {
		return ratedDate;
	}

	public void setRatedDate(String ratedDate) {
		this.ratedDate = ratedDate;
	}

}
