package com.dhenton9000.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;



 
 


@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {

	private int starRating;
	private String reviewListing;
	private Date stampDate = new Date();
	private Long id = null;
	 

	public ReviewDTO(Review rv) {
		this.setStampDate(rv.getStampDate());
		this.setStarRating(rv.getStarRating());
		this.setReviewListing(rv.getReviewListing());
		 
		this.setId(rv.getId() );
	}

	public ReviewDTO() {
	}

	public Review makeReview() {
		Review r = new Review();
		r.setStampDate(this.getStampDate());
		//r.setParentRestaurantId(this.getParentRestaurantId());
		r.setStarRating(this.getStarRating());
		r.setReviewListing(this.getReviewListing());
		if (this.getId() != null)
		{
			 
			r.setId(this.getId());
		}

		return r;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	public String getReviewListing() {
		return reviewListing;
	}

	public void setReviewListing(String reviewListing) {
		this.reviewListing = reviewListing;
	}



	public Date getStampDate() {
		return stampDate;
	}

	public void setStampDate(Date stampDate) {
		this.stampDate = stampDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 


}
