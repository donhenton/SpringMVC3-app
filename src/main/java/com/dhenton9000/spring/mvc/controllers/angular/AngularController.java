/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.controllers.angular;

import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.RestaurantDTO;
import com.dhenton9000.restaurant.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dhenton
 */
@Controller
@RequestMapping(value = "/angular/*")
public class AngularController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("/restaurant")
    public ModelAndView gotoAngularApp() {

        List<RestaurantDTO> restaurants = new ArrayList<RestaurantDTO>();

        List<Restaurant> rItems = this.getRestaurantService()
                .getAllRestaurants();
        for (Restaurant r : rItems) {
            restaurants.add(new RestaurantDTO(r));
        }

        ObjectMapper mapper = new ObjectMapper();
        Writer w = new StringWriter();

        try {
            mapper.writeValue(w, restaurants);
        } catch (Exception e) {
            throw new RuntimeException("problem with json\n" + e.getClass().getName() + "\n" + e.getMessage());
        }

        String jsonData = w.toString();
        return new ModelAndView("tiles.angular.restaurant", "jsonRestaurants",
                jsonData);
    }

    /**
     * @return the restaurantService
     */
    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    /**
     * @param restaurantService the restaurantService to set
     */
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

}
