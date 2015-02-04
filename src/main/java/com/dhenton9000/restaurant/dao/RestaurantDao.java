package com.dhenton9000.restaurant.dao;

import com.dhenton9000.jpa.dao.support.GenericDao;
import java.util.List;

import com.dhenton9000.restaurant.model.Restaurant;
 

public interface RestaurantDao extends GenericDao<Restaurant, Long>{

	 

	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurant(Long id);
	Long saveOrAddRestaurant(Restaurant t);
	void deleteRestaurant(Long key);
	//void deleteReview(Long key);
	List<Restaurant> getRestaurantsWithMaxRating(int maxRating);
	List<Restaurant> getRestaurantsLike(String searchString);
	
}

