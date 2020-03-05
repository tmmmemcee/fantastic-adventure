/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Location;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface LocationDao {
    void addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocation(long locationId);
    Location getLocationById(long locationId);
    List<Location> getAllLocations();
    Location findLocationForOrganization(long orgId);
    
    Location findLocationForSighting(long sightingId);
}
