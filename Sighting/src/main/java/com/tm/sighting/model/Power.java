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
public class Power {
    private long powerId;
    
    @NotEmpty(message = "You must supply a value for Power's Name.")
    @Length(max = 128, message = "Super Power's Name must be no more than 128 characters in length." )
    private String powerName;        
    
    @Length(max = 256, message = "Description must be less than 256 characters in length.")
    private String description;
    
    public void setPowerId(long id) {powerId=id;}
    public long getPowerId() {return powerId;}
    public void setPowerName(String name) {powerName= name;}
    public String getPowerName() {return powerName;}
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {return description;}
    

    @Override
    public String toString() {
        return "SuperPower";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.powerId ^ (this.powerId >>> 32));
        hash = 19 * hash + Objects.hashCode(this.powerName);
        hash = 19 * hash + Objects.hashCode(this.description);
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
        final Power other = (Power) obj;
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.powerName, other.powerName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
}
