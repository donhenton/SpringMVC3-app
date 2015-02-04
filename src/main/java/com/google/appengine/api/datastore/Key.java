/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.appengine.api.datastore;

/**
 *
 * @author dhenton
 */
public class Key {
    private Long id;
    
    
    public Key(){}
    public Key(Long id ){ this.id = id;}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Key other = (Key) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Key{" + "id=" + id + '}';
    }

    
    
    
    
    
    
}
