package com.dhenton9000.restaurant.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dhenton9000.jpa.domain.Identifiable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author dhenton
 */
@Entity
@Table(name = "RESTAURANT")
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT u FROM Restaurant u"),
    @NamedQuery(name = "Restaurant.nameLike", query = "SELECT u FROM Restaurant u WHERE u.name like  :searchString "),
    @NamedQuery(name = "Restaurant.findByid", query = "SELECT u FROM Restaurant u WHERE u.id = :id")})

@XmlRootElement
public class Restaurant implements Serializable, Identifiable<Long> {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotEmpty(message = "Restaurant Name cannot be blank")
    private String name;
    @NumberFormat(pattern = "####")
    private Integer version;

    @NotEmpty(message = "Zipcode cannot be blank")
    private String zipCode;

    @NotEmpty(message = "City cannot be blank")
    private String city;
    @NotEmpty(message = "State cannot be blank")
    private String state;
    private List<Review> reviews = new ArrayList<Review>();

    private final transient Logger logger = LoggerFactory
            .getLogger(Restaurant.class);

    public Restaurant(Long k) {
        id = k;
    }

    public Restaurant() {

    }

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id_seq")
    @SequenceGenerator(name = "restaurant_id_seq", sequenceName = "restaurant_id_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    @Override
    public void setPrimaryKey(Long id) {
        this.id = id;

    }

    @Column(name = "NAME", length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * @return the version
     */
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the zipCode
     */
    @Column(name = "ZIP_CODE", length = 255)
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the city
     */
    @Column(name = "CITY", length = 255)
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    @Column(name = "STATE", length = 255)
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String id = "null";

        if (getId() != null) {
            id = getId().toString();
        }
        return display(getName()) + "|zip " + display(getZipCode()) + "| {" + id + "}";
    }

     private String display(String t) {
        if (t == null) {
            return "null";
        } else {
            return t.trim();
        }
    }
    public void clear() {
        setName(null);
        setCity(null);
        setVersion(null);
        setZipCode(null);
        setId(null);
        setState(null);

    }

    @Override
    @Transient
    public Long getPrimaryKey() {
        if (getId() != null) {
            return getId();
        } else {
            return null;
        }
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    public List<Review> getReviews() {
        if (reviews == null) {
            reviews = new ArrayList<Review>();
        }
        return reviews;
    }

    public void addReview(int rating, String message) {
        Review r = new Review();
        r.setReviewListing(message);
        r.setStarRating(rating);

        reviews.add(r);

    }

    @Override
    @Transient
    public boolean isPrimaryKeySet() {
        return id != null;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
