/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.rest.client;

import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.RestaurantDTO;
import com.dhenton9000.restaurant.model.Review;
import com.dhenton9000.restaurant.service.RestaurantService;
import com.dhenton9000.spring.rest.controllers.BackBoneIdResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.BadRequestException;

/**
 * Jersey restaurant client using marshalling.
 * 
 * @author dhenton
 */
public class JerseyMarshallingRestaurantClient implements RestaurantService {

    private final URI baseURI;
    private final Client client;
    private static Logger LOG = LogManager.getLogger(JerseyMarshallingRestaurantClient.class);

    public JerseyMarshallingRestaurantClient(String baseURL) {

        baseURI = UriBuilder.fromUri(baseURL).build();
        this.client = ClientBuilder.newClient(createConfig());
        this.client.register(JacksonFeature.class);
    }

    private ClientConfig createConfig() {
        return new ClientConfig();
    }

    private WebTarget getWebTarget() {
        return client.target(getBaseURI());
    }

    private class ListRestaurantDtoType extends GenericType<List<RestaurantDTO>> {

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        ListRestaurantDtoType dtoListType = new ListRestaurantDtoType();
        List<RestaurantDTO> dtoThings
                = getWebTarget().path("restaurant").request().get(dtoListType);
        for (RestaurantDTO dto : dtoThings) {
            restaurants.add(dto.makeRestaurant());
        }

        return restaurants;
    }

    @Override
    public Restaurant getRestaurant(Long id) {
         

        WebTarget target = getWebTarget();
        RestaurantDTO rDTO = null;

        try {
            rDTO = target.path("restaurant").path(id.toString()).request()
                    .get(RestaurantDTO.class);
        } catch (NotFoundException err) {
            LOG.warn("Nothing found for id '" + id + "'");

        }

        if (rDTO == null) {
            return null;
        }

        return rDTO.makeRestaurant();

    }

    @Override
    public Long saveOrAddRestaurant(Restaurant t) {

        Long requestedId = t.getId();

        if (requestedId == null) {

            requestedId = addRestaurant(t);

        } else {
            Restaurant lookupRestaurant = getRestaurant(requestedId);
            if (lookupRestaurant == null) {
                String info
                        = String.format("could not find restaurant with id '%s''",
                                requestedId.toString());

                throw new NotFoundException(info);
            } else {
                WebTarget target = getWebTarget();
                Entity dataToSend = Entity.json(t);

                target.path("restaurant").path(requestedId.toString()).
                        request(MediaType.APPLICATION_JSON).
                        put(dataToSend);

            }

        }

        return requestedId;
    }

    private Long addRestaurant(Restaurant t) {
        WebTarget target = getWebTarget();
        Entity dataToSend = Entity.json(t);
        BackBoneIdResponse responseObj = target.path("restaurant").
                request(MediaType.APPLICATION_JSON).
                post(dataToSend, BackBoneIdResponse.class);
        return responseObj.getId();
    }

    @Override
    public void deleteRestaurant(Long key) {
        WebTarget target = getWebTarget();

        Restaurant lookupRestaurant = getRestaurant(key);
        if (lookupRestaurant == null) {
            String info
                    = String.format("could not find restaurant with id '%s''",
                            key.toString());

            throw new NotFoundException(info);
        }

        target.path("restaurant").path(key.toString()).
                request(MediaType.APPLICATION_JSON).
                delete();

    }

    @Override
    public List<Restaurant> getRestaurantsWithMaxRating(int ratingLimit) {

        ArrayList<Restaurant> foundRestaurants = new ArrayList<Restaurant>();
        List<Restaurant> restaurants = getAllRestaurants();
        for (Restaurant r : restaurants) {
            for (Review rev : r.getReviews()) {
                if (rev.getStarRating() >= ratingLimit) {
                    foundRestaurants.add(r);
                    break;
                }
            }

        }

        return foundRestaurants;

    }

    @Override
    public List<Restaurant> getRestaurantsLike(String searchString) {
        ArrayList<Restaurant> foundRestaurants = new ArrayList<Restaurant>();
        List<Restaurant> restaurants = getAllRestaurants();
        for (Restaurant r : restaurants) {

            if (r.getName().contains(searchString)) {
                foundRestaurants.add(r);
            }

        }

        return foundRestaurants;
    }

    @Override
    public void deleteReview(Long restaurantId, Long reviewId) {
        WebTarget target = getWebTarget();
        target.path("restaurant")
                .path("review").path("{restaurantId}")
                .path("{reviewId}")
                .resolveTemplate("restaurantId", restaurantId)
                .resolveTemplate("reviewId", reviewId)
                .request().delete();

    }

    @Override
    public Review addReview(Long restaurantId, Review newReview) {

        WebTarget target = getWebTarget();
        Entity dataToSend = Entity.json(newReview);
        BackBoneIdResponse responseObj = target.path("restaurant")
                .path("review").path("{restaurantId}")
                .resolveTemplate("restaurantId", restaurantId)
                .request(MediaType.APPLICATION_JSON).
                post(dataToSend, BackBoneIdResponse.class);

        newReview.setId(responseObj.getId());
        return newReview;

    }

    @Override
    public Review saveReview(Long restaurantId, Review newReview) {

        Long reviewId = newReview.getId();
        if (reviewId == null) {
            throw new BadRequestException("id cannot be null for saveReview in Review object");
        }

        WebTarget target = getWebTarget();
        Entity dataToSend = Entity.json(newReview);
        target.path("restaurant")
                .path("review").path("{restaurantId}")
                .path("{reviewId}")
                .resolveTemplate("restaurantId", restaurantId)
                .resolveTemplate("reviewId", reviewId)
                .request().put(dataToSend);
        return newReview;
    }

    /**
     * @return the baseURL
     */
    public URI getBaseURI() {
        return baseURI;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

}
