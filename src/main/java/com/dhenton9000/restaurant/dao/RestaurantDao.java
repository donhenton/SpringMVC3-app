package com.dhenton9000.restaurant.dao;

import com.dhenton9000.jpa.dao.support.GenericDao;
import java.util.List;

import com.dhenton9000.restaurant.model.Restaurant;
 

public interface RestaurantDao extends GenericDao<Restaurant, Long>{

	 

	List<Restaurant> getAllRestaurants();
	List<Restaurant> getRestaurantsWithMaxRating(int maxRating);
	List<Restaurant> getRestaurantsLike(String searchString);
	
}

