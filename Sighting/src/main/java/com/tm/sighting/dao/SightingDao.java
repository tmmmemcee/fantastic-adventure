/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Sighting;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface SightingDao {
    void addSighting(Sighting sighting);
    List<Sighting> getAllSightings();
    void deleteSighting(long id);
    void updateSighting(Sighting sighting);
    Sighting getSighting(long id);
    List<Sighting> getAllSightingsByDate(Date date);
    List<Sighting> getAllSightingsByLocation(long locationId);
    List<Sighting> getAllSightingsBySuperHero(long superHeroId);
    List<Sighting> getMostRecentSightings();
}
