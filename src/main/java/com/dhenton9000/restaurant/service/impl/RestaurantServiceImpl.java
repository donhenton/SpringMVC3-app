package com.dhenton9000.restaurant.service.impl;

import com.dhenton9000.jpa.dao.support.GenericDao;
import com.dhenton9000.jpa.service.support.GenericEntityServiceImpl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dhenton9000.restaurant.dao.RestaurantDao;
import com.dhenton9000.restaurant.dao.ReviewDao;
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
import com.dhenton9000.restaurant.service.RestaurantService;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantServiceImpl extends GenericEntityServiceImpl<Restaurant, Long> implements RestaurantService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private ReviewDao reviewDao;
    private static Logger log = LogManager
            .getLogger(RestaurantServiceImpl.class);

    @Override
    public List<Restaurant> getAllRestaurants() {

        return getRestaurantDao().getAllRestaurants();
    }

    @Override
    public Restaurant getRestaurant(Long id) {

        return this.getByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Long saveOrAddRestaurant(Restaurant t) {

        log.debug("save or add restaurant " + t.getPrimaryKey()+" "+t.getName());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Restaurant>> violations = validator.validate(t);
        Restaurant aa = null;

        log.debug("object " + t.toString() + " managed " + this.entityManager.contains(t) 
                + " count " + t.getReviews().size());
        if (violations.isEmpty()) {
            if (t.getPrimaryKey() == null) {
                log.debug("doing merge");
                aa = this.merge(t);
                return aa.getPrimaryKey();
            } else {
                log.debug("doing save");
                this.merge(t);
                return t.getPrimaryKey();
            }

        } else {
            HashMap<String, String> errors = new HashMap<String, String>();
            Iterator<ConstraintViolation<Restaurant>> iter = violations
                    .iterator();
            while (iter.hasNext()) {
                ConstraintViolation<Restaurant> violation = iter.next();
                errors.put(violation.getPropertyPath().toString(),
                        violation.getMessage());
                log.debug("saveOrAddRestaurant problem: " + violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            throw new ValidatorFailureException("errors found", errors);
        }

    }

    public RestaurantDao getRestaurantDao() {
        return restaurantDao;
    }

    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long key) {
        log.debug("hit deleteRestaurant " + key);
        this.deleteByPrimaryKey(key);
        // getRestaurantDao().deleteRestaurant(key);

    }

    @Override
    public List<Restaurant> getRestaurantsWithMaxRating(int ratingLimit) {

        return getRestaurantDao().getRestaurantsWithMaxRating(ratingLimit);
    }

    @Override
    public List<Restaurant> getRestaurantsLike(String searchString) {

        return getRestaurantDao().getRestaurantsLike(searchString);
    }

    @Override
    @Transactional
    public void deleteReview(Long restaurantId, Long reviewId) {
        Restaurant parent = getRestaurant(restaurantId);
        log.debug("hit deleteReview " + restaurantId + " " + reviewId);

        if (parent == null) {
            log.warn("could not find restaurant in delete review "
                    + restaurantId);
            return;
        }
        Set<Review> reviews = parent.getReviews();
        log.debug("before delete " + reviews.size());
        Iterator<Review> iterReview = reviews.iterator();

        while (iterReview.hasNext()) {
            Review rr = iterReview.next();
            log.debug("key review " + reviewId + " -- " + rr.getId());
            if (rr.getId().equals(reviewId)) {
                log.debug("hit delete " + rr);
                iterReview.remove();
                reviewDao.delete(rr);
                break;
            }
        }

        log.debug("after delete " + reviews.size());

        this.merge(parent);

    }

    @Override
    @Transactional
    public Review saveReview(Long restaurantId, Review newReview) {
        Restaurant parent = getRestaurant(restaurantId);
        //log.debug("hit saveReview "+restaurantId+" "+newReview.getId().getId());

        if (parent == null) {
            log.warn("could not find restaurant in saveReview " + restaurantId);
            return null;
        }
        log.debug("saveReview found parent " + parent.getPrimaryKey());

        Set<Review> reviews = parent.getReviews();
        Long reviewKey = newReview.getId();

        //Long reviewKeyLong = null;
        if (reviewKey == null) {
            log.warn("review key null in  saveReview " + restaurantId);
            return null;
        }
        log.debug("review Key to match: " + reviewKey);

        Iterator<Review> iterReview = reviews.iterator();
        while (iterReview.hasNext()) {
            Review oR = iterReview.next();
            if (oR.getId().equals(reviewKey)) {
                log.debug("found match ");
                oR.setReviewListing(newReview.getReviewListing());
                oR.setStarRating(newReview.getStarRating());
                this.reviewDao.merge(oR);
            }
        }

        this.saveOrAddRestaurant(parent);
        return newReview;
    }

    @Override
    @Transactional
    public Review addReview(Long restaurantId, Review newReview) {
        log.debug("hit addReview " + restaurantId);
        log.debug("review " + newReview);
        Restaurant parent = getRestaurant(restaurantId);
        if (parent == null) {
            log.warn("could not find restaurant in addReview " + restaurantId);
            return null;
        }
        log.debug("starting review dao");
        Set<Review> reviews = parent.getReviews();
        if (reviews == null) {
            reviews = new HashSet<Review>();
        }
        Set<Review> reviewCopy = new HashSet<Review>();
         for (Review r: reviews)
         {
             reviewCopy.add(r);
         }
         
        newReview.setRestaurant(parent);
        reviews.add(newReview);
        parent.setReviews(reviews);
         

        log.debug("finished reveiw dao");
        //parent.getReviews().add(rr);
        log.debug("start parent save");
        parent = this.merge(parent);
        reviews = parent.getReviews();
        reviews.removeAll(reviewCopy);
       

        return reviews.iterator().next();

    }

    @Override
    public GenericDao<Restaurant, Long> getDao() {
        return restaurantDao;
    }

    @Override
    public Restaurant getNew() {
        return new Restaurant();
    }

    @Override
    public Restaurant getNewWithDefaults() {
        return new Restaurant();
    }

}
