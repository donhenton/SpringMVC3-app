package com.dhenton9000.restaurant.dao.impl;

import com.dhenton9000.jpa.dao.hibernate.BaseHibernateGenericDaoImpl;
import com.dhenton9000.jpa.dao.support.NamedQueryUtil;
import com.dhenton9000.jpa.dao.support.SearchTemplate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dhenton9000.restaurant.dao.RestaurantDao;
import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.Review;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDaoImpl
        extends BaseHibernateGenericDaoImpl<Restaurant, Long>
        implements RestaurantDao {

    private static Logger log = LogManager.getLogger(RestaurantDaoImpl.class);

    public RestaurantDaoImpl() {
        super(Restaurant.class);
    }

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    @Override
    public void setNamedQueryUtil(NamedQueryUtil namedQueryUtil) {
        this.namedQueryUtil = namedQueryUtil;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Restaurant> getAllRestaurants() {

        SearchTemplate template = new SearchTemplate();
        template.setNamedQuery("Restaurant.findAll");

        List<Restaurant> res = this.find(new Restaurant(), template);

        return res;
    }

    @Override
    public List<Restaurant> getRestaurantsWithMaxRating(int ratingLimit) {
         SearchTemplate template = new SearchTemplate();
        template.setNamedQuery("Restaurant.maxRating");
        template.addParameter("ratingLimit", ratingLimit);

        List<Restaurant> found = this.find(new Restaurant(), template);
        return found;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Restaurant> getRestaurantsLike(String searchString) {

        SearchTemplate template = new SearchTemplate();
        template.setNamedQuery("Restaurant.nameLike");
        template.addParameter("searchString", "%"+searchString+"%");

        List<Restaurant> found = this.find(new Restaurant(), template);
        return found;
    }

}
