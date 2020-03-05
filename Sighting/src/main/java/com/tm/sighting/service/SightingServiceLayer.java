/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.service;

import com.tm.sighting.model.Location;
import com.tm.sighting.model.Sighting;
import com.tm.sighting.model.SuperHero;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface SightingServiceLayer {
    void addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocation(long locationId);
    Location getLocationById(long locationId);
    List<Location> getAllLocations();
    Location findLocationForOrganization(long orgId);

    Location findLocationForSighting(long sightingId);    

    void addSighting(Sighting sighting);
    List<Sighting> getAllSightings();
    void deleteSighting(long id);
    void updateSighting(Sighting sighting);
    Sighting getSighting(long id);
    List<Sighting> getAllSightingsByDate(Date date);
    List<Sighting> getAllSightingsByLocation(long locationId);
    List<Sighting> getAllSightingsBySuperHero(long superHeroId);
    List<Sighting> getMostRecentSightings();
    
    SuperHero getSuperHeroFromSighting(long sightingId);
    
}
