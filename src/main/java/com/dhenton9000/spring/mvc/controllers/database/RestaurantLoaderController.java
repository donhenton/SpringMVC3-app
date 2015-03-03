package com.dhenton9000.spring.mvc.controllers.database;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dhenton9000.restaurant.service.RestaurantService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@ApiIgnore
@RequestMapping(value = "/database/restaurant/load")
public class RestaurantLoaderController {
	
        @Autowired
	private RestaurantService restaurantService;
	
	private static Logger log = LogManager
			.getLogger(RestaurantLoaderController.class);
	
	public static final String RESTAURANT_LOAD_TILES = "tiles.database.load.restaurant";
	public static final String MESSAGE_KEY = "message";
	
	@RequestMapping(value = "main")
	public ModelAndView goToLoadRestaurants() {

		ModelAndView mav = new ModelAndView(RESTAURANT_LOAD_TILES);
		mav.addObject(MESSAGE_KEY,"");
		return mav;

	}
	
	 
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

}
