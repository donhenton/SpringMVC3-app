package com.dhenton9000.spring.mvc.jdo.service.impl;

import com.dhenton9000.restaurant.dao.RestaurantDao;
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.service.impl.RestaurantServiceImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

 
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 

public class ReviewServiceImplTest {

 /*
	private RestaurantServiceImpl restaurantService = null;
	private RestaurantDao restaurantDao = null;

	private static final String RESTAURANT_NAME = "alpha";
	 

	private final Logger logger = LoggerFactory
			.getLogger(ReviewServiceImplTest.class);
	 
	private Restaurant sampleRestaurant;
	 
	

	@Before
	public void setUp() {
		
		pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		
		
		helper.setUp();
		restaurantService = new RestaurantServiceImpl();
		restaurantDao = new RestaurantDaoImpl();
		restaurantService.setRestaurantDao(restaurantDao);
		
		Restaurant nR = new Restaurant();
		nR.setName(RESTAURANT_NAME);
		nR.setCity("city");
		nR.setState("Fred");
		nR.setVersion(1);
		nR.setZipCode("50504");
		Review r = null;
		
		r = new Review();
		r.setReviewListing("alpha");
		r.setStarRating(1);
		nR.getReviews().add(r);

		r = new Review();
		r.setReviewListing("beta");
		r.setStarRating(2);
		nR.getReviews().add(r);

		r = new Review();
		r.setReviewListing("gamma");
		r.setStarRating(3);
		nR.getReviews().add(r);
		
		sampleKey = restaurantService.saveOrAddRestaurant(nR);
		
		sampleRestaurant = restaurantService.getRestaurant(sampleKey.getId());

	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	

	@Test
	public void testDeleteReview2()
	{
		Review review2 = sampleRestaurant.getReviews().get(1);
		Key resKey = sampleRestaurant.getId();
		sampleRestaurant.getReviews().clear();
		sampleRestaurant.setName("qqq");
		restaurantService.saveOrAddRestaurant(sampleRestaurant);
		sampleRestaurant = restaurantService.getRestaurant(sampleKey.getId());
		assertEquals("qqq",sampleRestaurant.getName());
		assertEquals(0,sampleRestaurant.getReviews().size());
		
		
	}
	
	
	@Test
	public void testDeleteReview()
	{

		Long reviewId = sampleRestaurant.getReviews().get(1).getId().getId();
		Long restaurantId = sampleKey.getId();
		restaurantService.deleteReview(restaurantId, reviewId);
		sampleRestaurant = restaurantService.getRestaurant(sampleKey.getId());
		assertEquals(2,sampleRestaurant.getReviews().size());
		
		
	}
	
	@Test
	public void testAddReview()
	{

		Review rev = new Review(2,"xxx");
		assertNull(rev.getId());
		Long restaurantId = sampleKey.getId();
		Review n = restaurantService.addReview(restaurantId, rev);
		sampleRestaurant = restaurantService.getRestaurant(sampleKey.getId());
		assertEquals(4,sampleRestaurant.getReviews().size());
		assertEquals("xxx",sampleRestaurant.getReviews().get(3).getReviewListing());
		assertNotNull(n.getId());
		
		
	}
	
	@Test
	public void testModifyReview()
	{

		Review rev = sampleRestaurant.getReviews().get(1);
		rev.setStarRating(99);
		
		Long restaurantId = sampleKey.getId();
		Review n = restaurantService.saveReview(restaurantId, rev);
		sampleRestaurant = restaurantService.getRestaurant(sampleKey.getId());
		assertEquals(3,sampleRestaurant.getReviews().size());
		Review rev2 = sampleRestaurant.getReviews().get(1);
		assertEquals(99,rev2.getStarRating());
		
		
	}
*/	
	
}
