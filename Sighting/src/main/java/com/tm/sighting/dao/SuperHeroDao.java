/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.SuperHero;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface SuperHeroDao {
    SuperHero addSuper(SuperHero superHero);
    void updateSuper(SuperHero superHero);
    void deleteSuper(long superHeroId);
    SuperHero getSuper(long superHeroId);
    List<SuperHero> getAllSuperHeros();
    List<SuperHero> getAllSuperHerosByOrganization(long organizationId);
    SuperHero getSuperHeroFromSighting(long sightingId);
    
    
}
