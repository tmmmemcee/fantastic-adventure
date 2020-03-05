/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Location;
import com.tm.sighting.model.Sighting;
import com.tm.sighting.model.SuperHero;
import java.time.LocalDate;
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
public class SightingDaoTest {
    SightingDao dao;
    SuperHeroDao superDao;
    LocationDao locDao;
    
    public SightingDaoTest() {
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

        dao = ctx.getBean("SightingDao", SightingDao.class);
        superDao = ctx.getBean("SuperHeroDao", SuperHeroDao.class);
        locDao = ctx.getBean("LocationDao",LocationDao.class);
        // delete all books
        List<SuperHero> heros = superDao.getAllSuperHeros();
        for (SuperHero currentSuperHero : heros) {
            superDao.deleteSuper(currentSuperHero.getSuperHeroId());
        }
        dao.getAllSightings().stream().forEach(s -> dao.deleteSighting(s.getSightingId()));
        locDao.getAllLocations().stream().forEach(l -> locDao.deleteLocation(l.getLocationId()));
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddSighting() {
        Sighting sight = new Sighting();
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        sight.setSuperHero(hero);
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        locDao.addLocation(loc);
        sight.setLocation(loc);
        sight.setDate(LocalDate.now());
        
        dao.addSighting(sight);
        Sighting sight2 = dao.getSighting(sight.getSightingId());
        sight2.setLocation(loc);
        sight2.setSuperHero(hero);
        assertEquals(sight, sight2);
    }

    /**
     * Test of deleteSighting method, of class SightingDao.
     */
    @Test
    public void testDeleteSighting() {
        Sighting sight = new Sighting();
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        sight.setSuperHero(hero);
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        locDao.addLocation(loc);
        sight.setLocation(loc);
        sight.setDate(LocalDate.now());
        
        dao.addSighting(sight);
        assertNotNull(dao.getSighting(sight.getSightingId()));
        dao.deleteSighting(sight.getSightingId());
        assertNull(dao.getSighting(sight.getSightingId()));
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
        Sighting sight = new Sighting();
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        sight.setSuperHero(hero);
        Location loc = new Location();
        loc.setAddress("3205 longfellow Ave S");
        loc.setName("Headquarters");
        loc.setDescription("Hangout of MF DOOM");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        locDao.addLocation(loc);
        sight.setLocation(loc);
        sight.setDate(LocalDate.now());
        
//        dao.addSighting(sight);
//    
//        Sighting sight2 = dao.getSighting(sight.getSightingId());
//        sight2.setLocation(loc);
//        sight2.setSuperHero(hero);
//        assertEquals(sight, sight2);
//        hero.setSuperHeroName("diffferent");
//        dao.updateSighting(sight);
//        assertNotEquals(sight,dao.getSighting(sight.getSightingId()));
//        
    }

        /**
     * Test of getAllSuperHerosByLocation method, of class SuperHeroDao.
     */
    @Test
    public void testGetAllSightingsByLocation() {
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        superDao.addSuper(hero);
        Location loc = new Location();
        loc.setName("test location");
        loc.setLongitude("44.9444422");
        loc.setLatitude("-93.2480395");
        locDao.addLocation(loc);
        Sighting sighting = new Sighting();
        sighting.setSuperHero(hero);
        sighting.setLocation(loc);
        sighting.setDate(LocalDate.now());
        dao.addSighting(sighting);
        List<Sighting> sightings = dao.getAllSightingsByLocation(loc.getLocationId());
        assertEquals(sightings.size(), 1);
        
    }
    
}
