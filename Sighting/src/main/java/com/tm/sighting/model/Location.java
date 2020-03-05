/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tmmmemcee
 */
public class Location {
    private int locationId;
    private String longitude;
    private String latitude;
    private String name;
    @NotEmpty(message = "You must supply a value for a Location's Name.")
    @Length(max = 50, message = "Location's Name must be no more than 50 characters in length." )        
    private String description;
    private String address;
    public void setLocationId(int locationId) {this.locationId=locationId;}
    public int getLocationId() {return locationId;}
    public void setLongitude(String longitude){this.longitude= longitude;}
    public String getLongitude(){return longitude;}
    public void setLatitude(String latitude) {this.latitude=latitude;}
    public String getLatitude(){return latitude;}
    public void setName(String name){this.name=name;}
    public String getName() {return name;}
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {return description;}
    public void setAddress(String address) {this.address=address;}
    public String getAddress(){return address;}

    @Override
    public String toString() {
        return "Location";
    }
    
            
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.locationId;
        hash = 47 * hash + Objects.hashCode(this.longitude);
        hash = 47 * hash + Objects.hashCode(this.latitude);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.address);
        return hash;
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

}
