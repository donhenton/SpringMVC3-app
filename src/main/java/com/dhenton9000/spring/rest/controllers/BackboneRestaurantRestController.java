package com.dhenton9000.spring.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import com.dhenton9000.spring.rest.ObjectNotFoundException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dhenton9000.spring.mvc.controllers.ResourceNotFoundException;
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.RestaurantDTO;
import com.dhenton9000.restaurant.model.Review;
import com.dhenton9000.restaurant.model.ReviewDTO;
import com.dhenton9000.restaurant.service.RestaurantService;
import com.dhenton9000.spring.rest.NumberParsingException;
import org.springframework.beans.factory.annotation.Autowired;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "backbone/restaurant")
@Api(value = "/restaurants/", description = "Service for maintaining restaurants")
public class BackboneRestaurantRestController {

    private static Logger log = LogManager
            .getLogger(BackboneRestaurantRestController.class);
    @Autowired
    private RestaurantService restaurantService;

    // http://localhost:8888/app/backbone/restaurant/4723501952925696
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create restaurant", notes = "Create or save a restaurant")
    @ApiResponses({
        @ApiResponse(code = 201, message = "A new restaurant has been created"),
        @ApiResponse(code = 400, message = "validation error")})
    public @ResponseBody
    BackBoneIdResponse create(
            @ApiParam(value = "the new Restaurant Data", required = true)
            @RequestBody RestaurantDTO rDTO) {
        log.debug("starting create " + rDTO);
        Long k = getRestaurantService().saveOrAddRestaurant(
                rDTO.makeRestaurant());
        log.debug("hit created id " + k.toString());
        BackBoneIdResponse res = new BackBoneIdResponse();
        res.setId(k);
        return res;
    }

    /**
     * this will throw a ValidatorFailureException if there are problems this
     * will be handled by
     * com.dhenton9000.spring.rest.controllers.ControllerAdvisor
     *
     * @param rDTO
     * @param id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT,consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update restaurant", notes = "Update a restaurant")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurant updated"),
        @ApiResponse(code = 400, message = "validation error")})
    public void update(@ApiParam(value = "the new Restaurant Data", required = true) @RequestBody RestaurantDTO rDTO,
            @ApiParam(value = "the id of the restaurant to update", required = true) @PathVariable("id") String id) {
        log.debug("hit update id " + rDTO.getId());
        log.debug("restaurant name " + rDTO.getName());
        
        RestaurantDTO r = this.getRestaurant(id);
        if (r == null)
        {
            throw new ObjectNotFoundException("unable to find restaurant "+id);
        }
        
        getRestaurantService().saveOrAddRestaurant(rDTO.makeRestaurant());
    }

    @RequestMapping(value = "{restaurantId}", method = RequestMethod.GET , produces = "application/json")
    @ApiOperation(value = "get single restaurant", notes = "Get a single restaurant")
    @ApiResponses({
        @ApiResponse(code = 404, message = "restaurant not found")})
    public @ResponseBody
    RestaurantDTO getRestaurant(
            @ApiParam(value = "restaurant id", required = true) @PathVariable("restaurantId") String restaurantId) {
        log.debug("hit getRestaurant!!!!");
        Long key = null;

        try {
            key = Long.parseLong(restaurantId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + restaurantId);
        }
        Restaurant restaurant = this.getRestaurantService().getRestaurant(key);
        if (restaurant == null) {
            throw new ResourceNotFoundException("could not find key '" + key
                    + "'");
        } else {
            return new RestaurantDTO(restaurant);
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseClass handleResourceNotFoundException(ResourceNotFoundException b) {
        ErrorResponseClass response = new ErrorResponseClass(b);
        return response;

    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseClass handleObjectNotFoundException(ObjectNotFoundException b) {
        ErrorResponseClass response = new ErrorResponseClass(b);
        return response;

    }

    @RequestMapping(value = "{restaurantId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "delete single restaurant", notes = "Delete single restaurant and all reviews")
    @ApiResponses({
        @ApiResponse(code = 404, message = "restaurant not found")})
    public void remove(
            @ApiParam(value = "restaurant id", required = true)
            @PathVariable("restaurantId") String restaurantId) {
        Long key = null;
        log.debug("hit delete id " + restaurantId);
        try {
            key = Long.parseLong(restaurantId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + restaurantId
                    + " in delete");
        }

        try {
            getRestaurantService().deleteRestaurant(key);
        } catch (ObjectNotFoundException e) {
            throw new ResourceNotFoundException("cannot find restaurant with key " + key);
        }

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get all restaurants", notes = "Get the complete restaurant list with reviews")
    public @ResponseBody
    List<RestaurantDTO> getAllRestaurants() {
        log.debug("hit allRestaurant!!!!");
        List<RestaurantDTO> restaurants = new ArrayList<RestaurantDTO>();

        List<Restaurant> rItems = this.getRestaurantService()
                .getAllRestaurants();
        for (Restaurant r : rItems) {
            restaurants.add(new RestaurantDTO(r));
        }
        return restaurants;
    }

    // /////// REVIEWS/////////////////////////////////
    @RequestMapping(value = "/review/{restaurantId}/{reviewId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove a review", notes = "Remove a review for a restaurant")
    @ApiResponses({
        @ApiResponse(code = 404, message = "review or restaurant not found")})
    public void removeReview(
            @ApiParam(value = "restaurant id", required = true) @PathVariable("restaurantId") String restaurantId,
            @ApiParam(value = "review id", required = true) @PathVariable("reviewId") String reviewId) {
        Long restaurantIdLong = null;
        Long reviewIdLong = null;
        log.debug("hit removeReview " + restaurantId + " " + reviewId);
        try {
            restaurantIdLong = Long.parseLong(restaurantId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + restaurantId
                    + " in delete");
        }

        try {
            reviewIdLong = Long.parseLong(reviewId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + reviewId
                    + " in delete");
        }

        try {
            getRestaurantService().deleteReview(restaurantIdLong, reviewIdLong);
        } catch (ObjectNotFoundException e) {
            String info = String.format("Cannot find review %d for restaurant %d", reviewIdLong, restaurantIdLong);
            throw new ResourceNotFoundException(info);
        }

    }

    @RequestMapping(value = "/review/{restaurantId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "add a review", notes = "Add a review for a restaurant")
    @ApiResponses({
        @ApiResponse(code = 404, message = "restaurant not found")})
    public @ResponseBody
    BackBoneIdResponse addReview(@RequestBody ReviewDTO rDTO,
            @ApiParam(value = "restaurant id", required = true) @PathVariable("restaurantId") String restaurantId) {

        Long restaurantIdLong = null;
        log.debug("hit addReview " + restaurantId + " " + rDTO.getId());
        try {
            restaurantIdLong = Long.parseLong(restaurantId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + restaurantId
                    + " in createReview");
        }
        Review ret = getRestaurantService().addReview(restaurantIdLong,
                rDTO.makeReview());
        BackBoneIdResponse res = new BackBoneIdResponse();
        res.setId(ret.getId());
        return res;
    }

    @RequestMapping(value = "/review/{restaurantId}/{reviewId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "save a review", notes = "Save/update a review for a restaurant")
    @ApiResponses({
        @ApiResponse(code = 404, message = "review or restaurant not found")})
    public void saveReview(@RequestBody ReviewDTO rDTO,
            @ApiParam(value = "restaurant id", required = true) @PathVariable("restaurantId") String restaurantId,
            @ApiParam(value = "review id", required = true) @PathVariable("reviewId") String reviewId) {
        Long restaurantIdLong = null;
        log.debug("hit updateReview " + restaurantId + " " + rDTO.getId());

        try {
            restaurantIdLong = Long.parseLong(restaurantId);
        } catch (NumberFormatException e) {
            throw new NumberParsingException("Could not parse " + restaurantId
                    + " in updateReview");
        }

        getRestaurantService().saveReview(restaurantIdLong,
                rDTO.makeReview());
    }

    // ////////////////////////////////////////////////////////////////////////////////////
    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

}
