/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.service;

import com.tm.sighting.dao.LocationDao;
import com.tm.sighting.dao.SightingDao;
import com.tm.sighting.dao.SuperHeroDao;
import com.tm.sighting.model.Location;
import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Sighting;
import com.tm.sighting.model.SuperHero;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tmmmemcee
 */
public class SightingServiceLayerImpl implements SightingServiceLayer{
    LocationDao locationDao;
    SightingDao sightingDao;
    SuperHeroDao superDao;
    
    public SightingServiceLayerImpl(LocationDao locationDao, SightingDao sightingDao, 
            SuperHeroDao superDao) {
        this.locationDao = locationDao;
        this.sightingDao = sightingDao;
        this.superDao = superDao;
    }
    
    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public void deleteLocation(long locationId) {
        locationDao.deleteLocation(locationId);
    }

    @Override
    public Location getLocationById(long locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location findLocationForOrganization(long orgId) {
        return locationDao.findLocationForOrganization(orgId);
    }

    public void findLocationsForListOrganization(List<Organization> orgs) {
        orgs.stream()
                .forEach((Organization o) 
                        -> o.setLocation(findLocationForOrganization(o.getOrganizationId())));
    }

    public Location findLocationForSighting(long sightingId) {
        return locationDao.findLocationForSighting(sightingId);
    }

    @Override
    public void addSighting(Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        sightings.stream().forEach(s ->  {
            s.setSuperHero(getSuperHeroFromSighting(s.getSightingId()));
            s.setLocation(findLocationForSighting(s.getSightingId()));

        });
        return sightings;
    }

    @Override
    public void deleteSighting(long id) {
        sightingDao.deleteSighting(id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public Sighting getSighting(long id) {
        Sighting sighting = sightingDao.getSighting(id);
        sighting.setSuperHero(getSuperHeroFromSighting(id));
        sighting.setLocation(findLocationForSighting(id));

        return sighting;
    }

    @Override
    public List<Sighting> getAllSightingsByDate(Date date) {
        return sightingDao.getAllSightingsByDate(date);
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(long locationId) {
        return sightingDao.getAllSightingsByLocation(locationId);
    }

    @Override
    public List<Sighting> getAllSightingsBySuperHero(long superHeroId) {
        return sightingDao.getAllSightingsBySuperHero(superHeroId);
    }
    @Override
    public SuperHero getSuperHeroFromSighting(long sightingId) {
        return superDao.getSuperHeroFromSighting(sightingId);
    }
    
    @Override
    public List<Sighting> getMostRecentSightings() {
        List<Sighting> sightings = sightingDao.getMostRecentSightings();
        return sightings.stream().limit(10).collect(Collectors.toList());
    }
}
