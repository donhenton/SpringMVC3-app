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
    public Restaurant getRestaurant(Long id) {
        return this.findById(id);
    }

    @Override
    public Long saveOrAddRestaurant(Restaurant restaurantItem) {

        /*
         PersistenceManager pm = null;
         Key k = restaurantItem.getId();
         String info = "in saveOrAddRestaurant ";
         if (k != null) {
         long kvar = k.getId();
         info += "found key " + kvar;
         } else {
         info += " found key null";
         }
         Restaurant r = null;
         try {
         pm = pmf.getPersistenceManager();
         r = pm.makePersistent(restaurantItem);
         Long newRestaurantKey = r.getIdAsLong();
         if (r.getReviews() != null) {
         for (Review rv : r.getReviews()) {
         rv.setParentRestaurantId(newRestaurantKey);
         //rv.setRestaurant(restaurantItem);
         pm.makePersistent(rv);
         }
         }
         log.debug(info);
         } finally {
         pm.close();
         }
         return r.getId();
         */
        return null;
    }

    @Override
    public void deleteRestaurant(Long key) {
        Restaurant r = this.findById(key);
        this.delete(r);
    }
   /*
    @Override
    public void deleteReview(Long key) {
     
         PersistenceManager pm = null;
         try {
         pm = pmf.getPersistenceManager();
         Key id = KeyFactory.createKey("Review", key);
         Review t = pm.getObjectById(Review.class, id);
         pm.deletePersistent(t);
         } catch (NucleusObjectNotFoundException err) {
         log.warn("could not find review with id of " + key + " for delete");

         } finally {
         pm.close();
         }
         
    }
*/
    @Override
    public List<Restaurant> getRestaurantsWithMaxRating(int ratingLimit) {
        List<Restaurant> all = getAllRestaurants();
        ArrayList<Restaurant> found = new ArrayList<Restaurant>();
        for (Restaurant r : all) {
            boolean addToList = true;
            if (r.getReviews() != null) {
                for (Review rv : r.getReviews()) {
                    if (rv.getStarRating() > ratingLimit) {
                        addToList = false;
                        break;
                    }
                }
                if (addToList) {
                    found.add(r);
                }
            }
        }
        return found;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Restaurant> getRestaurantsLike(String searchString) {

        List<Restaurant> all = getAllRestaurants();
        ArrayList<Restaurant> found = new ArrayList<Restaurant>();
        for (Restaurant r : all) {
            if (r.getName().toUpperCase().contains(searchString.toUpperCase())) {
                found.add(r);
            }

        }
        return found;
    }

}
