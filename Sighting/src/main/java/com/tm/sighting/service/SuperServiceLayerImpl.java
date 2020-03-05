/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.service;

import com.tm.sighting.dao.LocationDao;
import com.tm.sighting.dao.OrganizationDao;
import com.tm.sighting.dao.PictureDao;
import com.tm.sighting.dao.PowerDao;
import com.tm.sighting.dao.SuperHeroDao;
import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Picture;
import com.tm.sighting.model.Power;
import com.tm.sighting.model.SuperHero;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tmmmemcee
 */
public class SuperServiceLayerImpl implements SuperServiceLayer{
    private static final String SERVER_ADDRESS = "/home/tmmmemcee/repos/softwareGuild/online-java-2019-tmmmemcee/SuperHero/Sighting/src/main/webapp/";
    private static final String pictureFolder = "images/";
    
    PowerDao powerDao;
    SuperHeroDao superHeroDao;
    PictureDao pictureDao;
    OrganizationDao organizationDao;
    LocationDao locationDao;
    
    public SuperServiceLayerImpl(PowerDao powerDao, SuperHeroDao superDao,
            PictureDao pictureDao, OrganizationDao orgDao, LocationDao locationDao) {
        this.powerDao=powerDao;
        this.superHeroDao = superDao;
        this.pictureDao = pictureDao;
        this.organizationDao = orgDao;
        this.locationDao = locationDao;
    }
    
    @Override
    public List<Power> getAllPowersFromIdArray(String[] ids) {
        List<Power> powers = new ArrayList<>();
        if (ids!=null) {
            Stream.of(ids).forEach((String s) -> powers.add(powerDao.getPowerById(Integer.parseInt(s))));
        }
        return powers;
    }

    @Override
    public void addPower(Power power) {
        powerDao.addPower(power);
    }

    @Override
    public void updatePower(Power power) {
        
        powerDao.updatePower(power);
    }

    @Override
    public Power getPowerById(long powerId) {
        return powerDao.getPowerById(powerId);
    }

    @Override
    public List<Power> getAllPowers() {
        return powerDao.getAllPowers();
    }

    @Override
    public void deletePower(long superHeroId) {
        powerDao.deletePower(superHeroId);
    }

    
    public List<Power> findPowersForSuperHero(long heroId) {
        return powerDao.findPowersForSuperHero(heroId);
    }

    public Picture addPicture(Picture picture, long id) {
        if (pictureDao.getPictureById(id) == null) {
            return pictureDao.addPicture(picture);
        }
        pictureDao.deletePicture(id);
        return pictureDao.addPicture(picture);
    }

    @Override
    public SuperHero addSuper(SuperHero superHero) {
        return superHeroDao.addSuper(superHero);
    }

    @Override
    public void updateSuper(SuperHero superHero) {
        SuperHero superOg = getSuper(superHero.getSuperHeroId());
        if (superHero.getSuperPowers().size() == 0) {
            superHero.setSuperPowers(superOg.getSuperPowers());
        }
        if (superHero.getOrganizations().size() == 0) {
            superHero.setOrganizations(superOg.getOrganizations());
        }
        
        
        superHeroDao.updateSuper(superHero);
    }

    @Override
    public void deleteSuper(long superHeroId) {
        superHeroDao.deleteSuper(superHeroId);
    }

    @Override
    public SuperHero getSuper(long superHeroId) {
        SuperHero sup =  superHeroDao.getSuper(superHeroId);
        associatePowersAndOrgs(sup);
        return sup;
    }

    @Override
    public List<SuperHero> getAllSuperHeros() {
        List<SuperHero> supers = superHeroDao.getAllSuperHeros();
        supers.stream().forEach(s -> associatePowersAndOrgs(s));
        return supers;
    }

    @Override
    public List<SuperHero> getAllSuperHerosByOrganization(long organizationId) {
        return superHeroDao.getAllSuperHerosByOrganization(organizationId);
    }

    
    public void associatePowersAndOrgs(SuperHero hero) {
        long superHeroId = hero.getSuperHeroId();
        hero.setOrganizations(organizationDao.getAllOrganizationsForSuperHero(superHeroId));
        hero.setSuperPowers(powerDao.findPowersForSuperHero(superHeroId));
        hero.setPicture(pictureDao.getPictureById(superHeroId));
    }
    
    
    public void uploadPhoto(String displayTitle, MultipartFile pictureFile, SuperHero hero, Model model) {
        // only save the pictureFile if the user actually uploaded something
        try {
            // we want to put the uploaded image into the 
            // <pictureFolder> folder of our application. getRealPath
            // returns the full path to the directory under Tomcat
            // where we can save files.
            String savePath = SERVER_ADDRESS + pictureFolder;
            File dir = new File(savePath);
            // if <pictureFolder> directory is not there, 
            // go ahead and create it
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // get the filename of the uploaded file - we'll use the
            // same name on the server.
            String filename = pictureFile.getOriginalFilename();
            // transfer the contents of the uploaded pictureFile to 
            // the server
            pictureFile.transferTo(new File(savePath + filename));

            // we successfully saved the pictureFile, now save a 
            // Picture to the DAO
            Picture picture = new Picture();
            picture.setFilename(pictureFolder + filename);
            picture.setTitle(displayTitle);
            picture.setPictureId(hero.getSuperHeroId());
            pictureDao.addPicture(picture);

            // redirect to home page to redisplay the entire album
            hero.setPicture(picture);
        } catch (Exception e) {
            // if we encounter an exception, add the error message 
            // to the model and return back to the pictureFile upload 
            // form page
            model.addAttribute("errorMsg", "File upload failed: " + 
                    e.getMessage());
        }
    } 

    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(long Id) {
        return organizationDao.getOrganizationById(Id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getAllOrganizationsFromIdArray(String[] ids) {
        return organizationDao.getAllOrganizationsFromIdArray(ids);
    }

    @Override
    public void findLocationsForListOrganization(List<Organization> orgs) {
        orgs.stream().forEach(o -> o.setLocation(locationDao.findLocationForOrganization(o.getOrganizationId())));
    }

    @Override
    public Picture getPictureById(long pictureId) {
        return pictureDao.getPictureById(pictureId);
    }
    
}
