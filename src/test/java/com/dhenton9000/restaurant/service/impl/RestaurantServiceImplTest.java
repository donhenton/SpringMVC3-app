package com.dhenton9000.restaurant.service.impl;

// transactions
// https://groups.google.com/forum/#!topic/google-appengine-java/VMg9xiQv1jM
//https://code.google.com/p/datanucleus-appengine/source/browse/#svn/trunk/tests/com/google/appengine/datanucleus/test/jdo
import com.dhenton9000.restaurant.dao.RestaurantDao;
import com.dhenton9000.restaurant.dao.ReviewDao;
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
import com.dhenton9000.restaurant.service.RestaurantService;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
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
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    RestaurantDao restaurantDao;
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
    public void testMaxRatingLimit() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        int totalCount = restaurants.size();
        restaurants = service.getRestaurantsWithMaxRating(4);
        assertTrue(totalCount > restaurants.size());
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

        r.setReviews(new HashSet<Review>());
        r.setCity("bonzo");
        r.setName("bonzo");
        r.setZipCode("12345");
        r.setState("ZZ");
        Long id = service.saveOrAddRestaurant(r);
        logger.debug("id was " + id);
        entityManager.flush();
        entityManager.clear();

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
        entityManager.clear();

        Restaurant otherR = service.getRestaurant(id);

        assertTrue(otherR.getName().contains("bonzo"));
        logger.debug("otherR " + otherR.getName());
        assertEquals(oldreviewCount + 1, otherR.getReviews().size());

    }

    @Test
    public void testMergeReview() {
        Long id = new Long(1);
        Restaurant res = service.getRestaurant(id);
        Review review = new Review(3, "fred");
        int oldReviewCount = 0;
        // review.setRestaurant(res);
        Set<Review> reviews = res.getReviews();
        if (reviews == null) {
            reviews = new HashSet<Review>();

        }
        oldReviewCount = reviews.size();
        ArrayList<Long> oldReviewKeys = new ArrayList<Long>();
        for (Review rr : reviews) {
            oldReviewKeys.add(rr.getId());
        }
        logger.debug("old reviews " + reviews);
        reviews.add(review);
        res.setReviews(reviews);

        restaurantDao.merge(res);

        entityManager.flush();
        entityManager.clear();

        Restaurant otherR = service.getRestaurant(id);
        logger.debug("new reviews " + otherR.getReviews());
        assertEquals(oldReviewCount + 1, otherR.getReviews().size());

    }

    @Test
    public void testAddReview() {
        logger.debug("Z1");

        Long id = new Long(1);
        Restaurant res = service.getRestaurant(id);
        logger.debug("Z1");
        Set<Review> reviews = res.getReviews();
        ArrayList<Long> oldReviewKeys = new ArrayList<Long>();
        for (Review r : reviews) {
            oldReviewKeys.add(r.getPrimaryKey());
        }
        int oldReviewCount = res.getReviews().size();
        assertEquals(3, oldReviewCount);
        logger.debug("XXXXX");
        Review review = new Review(3, "fred");
        assertNull(review.getPrimaryKey());
        entityManager.flush();
        entityManager.clear();
        Review newReview = service.addReview(id, review);

        entityManager.flush();
        entityManager.clear();
        assertNotNull(newReview.getPrimaryKey());

        Restaurant otherR = service.getRestaurant(id);
        assertEquals(oldReviewCount + 1, otherR.getReviews().size());
        assertFalse(oldReviewKeys.contains(newReview.getPrimaryKey()));

    }

    @Test
    public void testSaveReview() {
        //List<Restaurant> restaurants = service.getAllRestaurants();
        Long id = (long) 1; //restaurants.get(0).getPrimaryKey();
        Restaurant res = service.getRestaurant(id);
        Set<Review> reviews = res.getReviews();
        assertTrue(reviews.size() > 0);
        int reviewCount = reviews.size();
        Review rev = reviews.iterator().next();
        Long revId = rev.getId();

        rev.setStarRating(-99);

        Review rrr = service.saveReview(id, rev);
        entityManager.flush();
        entityManager.clear();

        res = service.getRestaurant(id);
        assertEquals(reviewCount, res.getReviews().size());
        assertEquals(-99, rrr.getStarRating().longValue());
        boolean foundIt = false;
        for (Review rr : res.getReviews()) {
            logger.debug("review key " + rr.getId());
            if (rr.getId().equals(revId)) {
                assertEquals(-99, rr.getStarRating().longValue());
                foundIt = true;
            }

        }
        assertTrue("could not find review " + revId, foundIt);

    }

    @Test
    public void testDeleteReview() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        Long id = new Long(1);
        Restaurant res = service.getRestaurant(id);
        Set<Review> reviews = res.getReviews();
        assertTrue(reviews.size() > 0);
        int oldReviewSize = reviews.size();
        Review rev = reviews.iterator().next();
        service.deleteReview(id, rev.getPrimaryKey());
        entityManager.flush();
        entityManager.clear();
        res = service.getRestaurant(id);
        assertEquals(oldReviewSize - 1, res.getReviews().size());

    }
}
