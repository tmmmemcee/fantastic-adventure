/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Power;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface PowerDao {
    void addPower(Power power);
    void updatePower(Power power);
    Power getPowerById(long powerId);
    List<Power> getAllPowers();
    void deletePower(long superHeroId);
    List<Power> findPowersForSuperHero(long heroId);

}
