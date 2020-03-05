/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author tmmmemcee
 */
public class Sighting {
    private long sightingId;
    private Location location;
    private SuperHero superHero;
    private LocalDate date;
    public void setSightingId(long id){
        sightingId = id;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setSuperHero(SuperHero hero) {
        this.superHero=hero;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Location getLocation() {
        return location;
    }
    public SuperHero getSuperHero(){
        return superHero;
    }
    public LocalDate getDate() {
        return date;
    }
    public long getSightingId() {
        return sightingId;
    }

    @Override
    public String toString() {
        return "Sighting";
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.sightingId ^ (this.sightingId >>> 32));
        hash = 23 * hash + Objects.hashCode(this.location);
        hash = 23 * hash + Objects.hashCode(this.superHero);
        hash = 23 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superHero, other.superHero)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

}
