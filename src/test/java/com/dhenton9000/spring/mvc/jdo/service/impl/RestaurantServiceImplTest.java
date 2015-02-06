package com.dhenton9000.spring.mvc.jdo.service.impl;

// transactions
// https://groups.google.com/forum/#!topic/google-appengine-java/VMg9xiQv1jM
//https://code.google.com/p/datanucleus-appengine/source/browse/#svn/trunk/tests/com/google/appengine/datanucleus/test/jdo
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
import com.dhenton9000.restaurant.service.RestaurantService;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-restaurant-config.xml"})
@Transactional
public class RestaurantServiceImplTest {

    @PersistenceContext()
    private EntityManager entityManager;
//    @Autowired
//    RestaurantDao dao;
    @Autowired
    RestaurantService service;

    private final Logger logger = LoggerFactory
            .getLogger(RestaurantServiceImplTest.class);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testServicesLoaded() {
        assertNotNull(service);

    }

    @Test
    public void testLoadAllRestaurants() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        int collectionSize = restaurants.size();
        assertTrue(10 < collectionSize);
        Restaurant r1 = restaurants.get(0);
        service.deleteRestaurant(r1.getPrimaryKey());

        restaurants = service.getAllRestaurants();
        int collectionSizeNew = restaurants.size();
        assertEquals(collectionSize, collectionSizeNew + 1);

    }

    @Test
    public void testStringSearch() {

        List<Restaurant> restaurants = service.getRestaurantsLike("Subway");
        assertEquals(6, restaurants.size());
    }

    @Test
    public void testGetRestaurantById() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        Long id = restaurants.get(0).getPrimaryKey();
        Restaurant otherR = service.getRestaurant(id);
        assertNotNull(otherR);
        assertEquals(id, otherR.getPrimaryKey());
    }

    @Test
    public void testAddRestaurant() {
        Restaurant r = new Restaurant();

        r.setReviews(new ArrayList<Review>());
        r.setCity("bonzo");
        r.setName("bonzo");
        r.setZipCode("12345");
        r.setState("ZZ");
        Long id = service.saveOrAddRestaurant(r);
        logger.debug("id was " + id);
        entityManager.flush();

        Restaurant otherR = service.getRestaurant(id);
        logger.debug("otherR " + otherR.getCity());
        r.setId(id);
        assertEquals(r, otherR);

    }

    @Test
    public void testSaveRestaurant() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        Long id = restaurants.get(0).getPrimaryKey();
        Restaurant r = service.getRestaurant(id);
        String oldName = r.getName();
        logger.debug("length " + oldName.length());
        assertEquals(255, oldName.length());

        int oldreviewCount = r.getReviews().size();
        Review review = new Review(3, "fred");
        r.getReviews().add(review);

        r.setName("bonzo");

        service.saveOrAddRestaurant(r);

        entityManager.flush();

        Restaurant otherR = service.getRestaurant(id);

        assertTrue(otherR.getName().contains("bonzo"));
        logger.debug("otherR " + otherR.getName());
        assertEquals(oldreviewCount + 1, otherR.getReviews().size());

    }

    @Test
    public void testAddReview() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        Long id = restaurants.get(0).getPrimaryKey();
        Restaurant res = service.getRestaurant(id);
        List<Review> reviews = res.getReviews();
        ArrayList<Long> oldReviewKeys = new ArrayList<Long>();
        for (Review r : reviews) {
            oldReviewKeys.add(r.getPrimaryKey());
        }
        int oldReviewCount = res.getReviews().size();
        assertTrue (oldReviewCount > 1);
        Review review = new Review(3, "fred");
        assertNull(review.getPrimaryKey());

        Review newReview  = service.addReview(id, review);
       
        entityManager.flush();
        assertNotNull(newReview.getPrimaryKey());
       
        Restaurant otherR = service.getRestaurant(id);
        assertEquals(oldReviewCount + 1, otherR.getReviews().size());
        assertFalse(oldReviewKeys.contains(newReview.getPrimaryKey()));

    }
}
