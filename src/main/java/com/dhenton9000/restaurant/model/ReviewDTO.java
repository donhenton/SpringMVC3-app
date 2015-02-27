package com.dhenton9000.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {

    @ApiModelProperty(required = true,
            dataType = "integer",
            position = 1,
            notes = "numeric rating of the restaurant")
    private int starRating;
    @ApiModelProperty(required = true,
            dataType = "String",
            position = 2,
            notes = "free form review text")
    private String reviewListing;
    @ApiModelProperty(required = false,
            
            position = 3,
            notes = "timestamp of the review change")
    private Date stampDate = new Date();
    @ApiModelProperty(required = true,
            dataType = "long",
            position = 4,
            notes = "id for the review")
    private Long id = null;

    public ReviewDTO(Review rv) {
        this.setStampDate(rv.getStampDate());
        this.setStarRating(rv.getStarRating());
        this.setReviewListing(rv.getReviewListing());

        this.setId(rv.getId());
    }

    public ReviewDTO() {
    }

    public Review makeReview() {
        Review r = new Review();
        r.setStampDate(this.getStampDate());
        //r.setParentRestaurantId(this.getParentRestaurantId());
        r.setStarRating(this.getStarRating());
        r.setReviewListing(this.getReviewListing());
        if (this.getId() != null) {

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
        this.reviewListing = clean(reviewListing);
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
    private String clean(String t)
    {
        if (t == null) return null;
        return t.trim();
    }

}
