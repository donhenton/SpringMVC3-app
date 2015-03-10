/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.rest.client;

import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
import java.util.List;
import javax.ws.rs.NotFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author dhenton
 */
public class MarshallingClientTests {

    private JerseyMarshallingRestaurantClient client;
    private static Logger LOG = LogManager.getLogger(MarshallingClientTests.class);

    public MarshallingClientTests() {
        client = new JerseyMarshallingRestaurantClient("http://localhost:8080/app/backbone");
    }

    @Test
    public void testGetRestaurantById() {
        Restaurant r = client.getRestaurant(new Long(4));
        assertTrue(r.getName().contains("Arby"));
    }

    @Test
    public void testGetRestaurantByIdWhenNothingsThere() {
        Restaurant r = client.getRestaurant(new Long(-44));
        assertNull(r);
    }

    @Test
    public void testRestaurantd() {
        List<Restaurant> r = client.getAllRestaurants();
        assertTrue(r.size() > 5);
    }

    @Test(expected = NotFoundException.class)
    public void testModifyRestaurantAndError() {
        Restaurant r = client.getRestaurant(new Long(14));
        r.setId(new Long(-34));
        client.saveOrAddRestaurant(r);

    }

    @Test
    public void testModifyRestaurant() {
        final Long cId = (long) 14;
        Restaurant r = client.getRestaurant(cId);
        String oldName = r.getName();
        final String newName = "bonzobonzo";

        r.setName(newName);
        Long id = client.saveOrAddRestaurant(r);
        assertEquals(id, cId);

        r = client.getRestaurant(new Long(14));
        assertEquals(r.getName(), newName);

        r.setName(oldName);
        client.saveOrAddRestaurant(r);

    }

    @Test
    public void testReviewsGetPopulated() {
        Restaurant r = client.getRestaurant(new Long(1));
        assertNotNull(r.getReviews());
        assertTrue(r.getReviews().size() > 0);
    }

    @Test(expected = BadRequestException.class)
    public void testAddBadRestaurant() {
        Restaurant r = client.getRestaurant(new Long(14));
        final String newName = null;
        r.setId(null);
        r.setName(newName);

        client.saveOrAddRestaurant(r);

    }

    @Test
    public void testAddAndDeleteRestaurant() {
        Restaurant r = client.getRestaurant(new Long(14));
        final String newName = "bonzobonzo";
        r.setId(null);
        r.setName(newName);

        Long id = client.saveOrAddRestaurant(r);

        r = client.getRestaurant(id);
        assertEquals(r.getName(), newName);

        client.deleteRestaurant(id);

        assertNull(client.getRestaurant(id));

    }

    @Test(expected = NotFoundException.class)
    public void testDeleteRestaurantWithNothingThere() {

        client.deleteRestaurant(new Long(-34));

    }

    //////////reviews /////////////////////////////////////////
    @Test
    public void testAddAndDeleteReview() {
        final Long restaurantId = (long) 1;
        Restaurant r = client.getRestaurant(restaurantId);
        Review newReview = r.getReviews().iterator().next();
        newReview.setId(null);
        int reviewCount = r.getReviews().size();

        String newReviewText = "BONZO";

        newReview.setReviewListing(newReviewText);

        Review reviewObj = client.addReview(restaurantId, newReview);

        r = client.getRestaurant(restaurantId);
        assertEquals(reviewCount + 1, r.getReviews().size());

        assertNotNull(reviewObj.getId());

        client.deleteReview(restaurantId, reviewObj.getId());

        r = client.getRestaurant(restaurantId);
        assertEquals(reviewCount, r.getReviews().size());

    }

    @Test
    public void testModifyReview() {
        final Long restaurantId = (long) 1;
        Restaurant r = client.getRestaurant(restaurantId);
        Review targetReview = r.getReviews().iterator().next();
        LOG.debug(targetReview);
        
        assertNotNull(targetReview.getId());
        int reviewCount = r.getReviews().size();

        String newReviewText = "BONZO";
        String oldReviewText = targetReview.getReviewListing();
        targetReview.setReviewListing(newReviewText);
        client.saveReview(restaurantId, targetReview);

        r = client.getRestaurant(restaurantId);
        assertEquals(reviewCount, r.getReviews().size());
        boolean foundIt = false;
        for (Review rev: r.getReviews())
        {
            if (rev.getReviewListing().trim().equals(newReviewText))
            {
                foundIt = true;
            }
        }
        assertTrue(foundIt);
        targetReview.setReviewListing(oldReviewText);
        client.saveReview(restaurantId,targetReview );

    }

}
