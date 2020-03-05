/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Location;
import com.tm.sighting.model.Organization;
import com.tm.sighting.model.SuperHero;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tmmmemcee
 */
public class LocationDaoTest {
    LocationDao dao;
    
    public LocationDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
        = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("LocationDao",LocationDao.class);
        dao.getAllLocations().stream().forEach(l -> dao.deleteLocation(l.getLocationId()));
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddLocation() {
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        dao.addLocation(loc);
        assertEquals(loc, dao.getLocationById(loc.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        dao.addLocation(loc);
        loc.setName("different location name");
        assertNotEquals(loc, dao.getLocationById(loc.getLocationId()));
        dao.updateLocation(loc);
        assertEquals(loc, dao.getLocationById(loc.getLocationId()));
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        dao.deleteLocation(loc.getLocationId());
        assertEquals(0, dao.getAllLocations().size());
    }

    /**
     * Test of getAllLocationBySuperHero method, of class LocationDao.
     */
    @Test
    public void testGetAllLocationBySuperHero() {
    }

    /**
     * Test of getAllSightingLocationsByDate method, of class LocationDao.
     */
    @Test
    public void testGetAllSightingLocationsByDate() {
    }

    /**
     * Test of findLocationForOrganization method, of class LocationDao.
     */
    @Test
    public void testFindLocationForOrganization() {
    }

    /**
     * Test of associateLocationWithOrganizations method, of class LocationDao.
     */
    @Test
    public void testAssociateLocationWithOrganizations() {
    }

    /**
     * Test of findLocationForSighting method, of class LocationDao.
     */
    @Test
    public void testFindLocationForSighting() {
    }

    
}
