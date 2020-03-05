/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.service;

import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Picture;
import com.tm.sighting.model.Power;
import com.tm.sighting.model.SuperHero;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tmmmemcee
 */
public interface SuperServiceLayer {
    void addPower(Power power);
    void updatePower(Power power);
    Power getPowerById(long powerId);
    List<Power> getAllPowers();
    void deletePower(long superHeroId);
    List<Power> getAllPowersFromIdArray(String[] ids);

    Organization addOrganization(Organization organization);
    void deleteOrganization(long organizationId);
    void updateOrganization(Organization organization);
    Organization getOrganizationById(long Id);
    List<Organization> getAllOrganizations();
    List<Organization> getAllOrganizationsFromIdArray(String[] ids);
    
    void findLocationsForListOrganization(List<Organization> orgs);
    
    void uploadPhoto(String displayTitle, MultipartFile pictureFile, SuperHero hero, Model model);
//    public Picture addPicture(Picture picture, long id);
//    public void deletePicture(long pictureId);
    public Picture getPictureById(long pictureId);
//    public List<Picture> getAllPictures();

    SuperHero addSuper(SuperHero superHero);
    void updateSuper(SuperHero superHero);
    void deleteSuper(long superHeroId);
    SuperHero getSuper(long superHeroId);
    List<SuperHero> getAllSuperHeros();
    List<SuperHero> getAllSuperHerosByOrganization(long organizationId);
    
    
    
}
