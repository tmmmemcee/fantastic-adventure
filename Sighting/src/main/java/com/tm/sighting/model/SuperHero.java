/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.model;

import com.tm.sighting.dao.Duality;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tmmmemcee
 */
public class SuperHero {
    long superHeroId;
    
    @NotBlank(message = "Super's Name can not be blank.")
    @NotEmpty(message = "You must supply a value for Super's Name.")
    @Length(max = 128, message = "Super's Name must be no more than 128 characters in length." )
    String superHeroName;
    
    @Length(max = 256, message = "Description must be no more than 256 characters in length." )
    String description;
    
    
    Duality usePowerForGood;
    
    List<Power> superPowers;
    List<Organization> organizations;
    Picture picture;

    public void setSuperHeroName(String name) {superHeroName = name;}
    public String getSuperHeroName() {return superHeroName;}
    public void setSuperHeroId(long id) {superHeroId=id;}
    public long getSuperHeroId() {return superHeroId;}
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {return description;}
    public void setOrganizations(List<Organization> orgs) {organizations = orgs;}
    public List<Organization> getOrganizations() {return organizations;}
    public void setSuperPowers(List<Power> powers) {superPowers = powers;}
    public List<Power> getSuperPowers() {return superPowers;}
    public void setUsePowerForGood(boolean usePowerForGood) {
        if (usePowerForGood) {
            this.usePowerForGood = Duality.HERO;
        } else {
        this.usePowerForGood = Duality.VILLIAN;
        }
    }
    public boolean getUsePowerForGood(){
        if (usePowerForGood == Duality.HERO) 
        {return true;}
        return false;
    }
    
    public void setPicture(Picture pic) {
        this.picture = pic;
    }
    public Picture getPicture(){
        return picture;
    }
    
    @Override
    public String toString() {
        return "SuperHero";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (int) (this.superHeroId ^ (this.superHeroId >>> 32));
        hash = 19 * hash + Objects.hashCode(this.superHeroName);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.usePowerForGood);
        hash = 19 * hash + Objects.hashCode(this.superPowers);
        hash = 19 * hash + Objects.hashCode(this.organizations);
        hash = 19 * hash + Objects.hashCode(this.picture);
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
        final SuperHero other = (SuperHero) obj;
        if (this.superHeroId != other.superHeroId) {
            return false;
        }
        if (!Objects.equals(this.superHeroName, other.superHeroName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.usePowerForGood != other.usePowerForGood) {
            return false;
        }
        if (!Objects.equals(this.superPowers, other.superPowers)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        return true;
    }

    
}
