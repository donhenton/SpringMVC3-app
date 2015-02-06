package com.dhenton9000.restaurant.service;

import java.util.List;

import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
 

public interface RestaurantService {

	
	List<Restaurant> getAllRestaurants();//
	Restaurant getRestaurant(Long id);//
	Long saveOrAddRestaurant(Restaurant t);//
	void deleteRestaurant(Long key);//
	List<Restaurant> getRestaurantsWithMaxRating(int ratingLimit);
	List<Restaurant> getRestaurantsLike(String searchString); //
	void deleteReview(Long restaurantId, Long reviewId);
	Review addReview(Long restaurantId, Review newReview);
	Review saveReview(Long restaurantId, Review newReview);
	
	 
	
}
