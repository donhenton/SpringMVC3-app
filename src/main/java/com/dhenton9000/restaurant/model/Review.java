package com.dhenton9000.restaurant.model;

import com.dhenton9000.jpa.domain.Identifiable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "REVIEWS")
@XmlRootElement
public class Review implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NumberFormat(pattern = "####")
    @NotNull(message = "must have a rating")
    private Integer starRating;
    
    private String reviewListing;
    private Restaurant restaurant;
    private Date stampDate = new Date();

//    private Restaurant parentRestaurant;
    private final transient Logger logger = LoggerFactory.getLogger(Review.class);

    public Review(Long k) {

        id = k;
    }

    public Review() {

    }

    public Review(int s, String m) {

        starRating = s;
        reviewListing = m;
    }

    @Column(name = "STAR_RATING")
    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    @Column(name = "REVIEW_LISTING")
    public String getReviewListing() {
        return reviewListing;
    }

    public void setReviewListing(String reviewListing) {
        this.reviewListing = reviewListing;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq")
    @SequenceGenerator(name = "reviews_id_seq", sequenceName = "reviews_id_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((reviewListing == null) ? 0 : reviewListing.hashCode());
        result = prime * result + starRating;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Review other = (Review) obj;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        /*
         if (id == null) {
         if (other.id != null) {
         return false;
         }
         } else if (!id.equals(other.id)) {
         return false;
         }
         if (reviewListing == null) {
         if (other.reviewListing != null) {
         return false;
         }
         } else if (!reviewListing.equals(other.reviewListing)) {
         return false;
         }
         return starRating.equals(other.starRating);
         */
        return true;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", starRating=" + starRating
                + ", reviewListing= '" + display(reviewListing) + "'] (parent " + restaurant+")";
    }

    private String display(String t) {
        if (t == null) {
            return "null";
        } else {
            return t.trim();
        }
    }

    @Column(name = "STAMP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RESTAURANT_ID", referencedColumnName = "ID", nullable
            = false, insertable = false, updatable = false)
    public Restaurant
            getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant parentRestaurant) {
        this.restaurant = parentRestaurant;
    }
*/
    @Override
    @Transient
    public Long getPrimaryKey() {
        if (getId() != null) {
            return getId();
        } else {
            return null;
        }
    }

    @Override
    @Transient
    public boolean isPrimaryKeySet() {
        return id != null;
    }

    @Override
    public void setPrimaryKey(Long id) {
        this.id = id;
    }

     /**
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RESTAURANT_ID", referencedColumnName = "ID", nullable = false,
            insertable = false, updatable = false)

    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param parent the parent to set
     */
    public void setRestaurant(Restaurant parent) {
        this.restaurant = parent;
    }

}
