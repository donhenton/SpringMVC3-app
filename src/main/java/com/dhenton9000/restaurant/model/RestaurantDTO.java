package com.dhenton9000.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public final class RestaurantDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true,
            dataType = "String",
            position = 1,
            notes = "name of the Restaurant")
    private String name;
    @ApiModelProperty(required = true,
            dataType = "String",
            position = 2,
            notes = "zip Code of Location")
    private String zipCode;
    @ApiModelProperty(required = true,
            dataType = "String",
            position = 3,
            notes = "City Location")
    private String city;
    @ApiModelProperty(required = true,
            dataType = "String",
            position = 4,
            notes = "City Location")
    private String state;
    @ApiModelProperty(required = false,
            dataType = "integer",
            position = 5,
            notes = "Versioning number")

    private Integer version;
    @ApiModelProperty(required = true,
            dataType = "long",
            position = 6,
            notes = "id")
    private Long id;
    @ApiModelProperty(required = false,
            dataType = "long",
            position = 7,
            notes = "optional review listings")
    private ArrayList<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();

    public RestaurantDTO() {

    }

    public RestaurantDTO(Restaurant r) {

        this.setCity(r.getCity());
        this.setName(r.getName());
        this.setZipCode(r.getZipCode());
        this.setState(r.getState());
        if (r.getVersion() != null) {
            this.setVersion(r.getVersion());
        }
        if (r.getId() != null) {
            this.setId(r.getId());
        }
        if (r.getReviews() != null) {
            for (Review rv : r.getReviews()) {
                this.getReviewDTOs().add(new ReviewDTO(rv));
            }
        }

    }

    public Restaurant makeRestaurant() {
        Restaurant r = new Restaurant();
        r.setCity(this.getCity());
        r.setName(this.getName());
        r.setZipCode(this.getZipCode());
        r.setState(this.getState());
        if (this.getVersion() != null) {
            r.setVersion(this.getVersion());
        }
        if (this.getId() != null) {

            r.setId(this.getId());
        }
        if (this.getReviewDTOs() != null) {
            for (ReviewDTO rd : getReviewDTOs()) {
                r.addReview(rd);
                 
            }
        }

        return r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = clean(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if ((this.getId() == null) ? (other.getId() != null) : !this.getId()
                .equals(other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return  zipCode ;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = clean(zipCode);
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = clean(city);
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = clean(state);
    }

    @Override
    public String toString() {

        return getName() + "|" + getZipCode() + "| {" + getId() + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addReviewDTO(int rating, String message) {
        ReviewDTO r = new ReviewDTO();
        r.setReviewListing(message);
        r.setStarRating(rating);
        getReviewDTOs().add(r);

    }

    public ArrayList<ReviewDTO> getReviewDTOs() {
        return reviewDTOs;
    }
    
    private String clean(String t)
    {
         if (t == null) return null;
         return t.trim();
         
    }

}
