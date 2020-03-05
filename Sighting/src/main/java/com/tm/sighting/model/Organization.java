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
public class Organization {
    private int organizationId;
    private String phone; 
    private String name;
    @NotEmpty(message = "You must supply a value for Organization's Name.")
    @Length(max = 50, message = "Organization's Name must be no more than 50 characters in length." )        
    private String description;
    private Location location;

    public void setOrganizationId(int organizationId)
    {
        this.organizationId=organizationId;
    }
    public int getOrganizationId(){return organizationId;}
    public void setPhone(String phone){this.phone=phone;}
    public String getPhone(){return phone;}
    public void setName(String name) {this.name=name;}
    public String getName(){return name;}
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {return description;}    
    public void setLocation(Location loc) {location=loc;}
    
    public Location getLocation() {return location;}
    
    @Override
    public String toString() {
        return "Organization";
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.organizationId;
        hash = 19 * hash + Objects.hashCode(this.phone);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.location);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    
}
