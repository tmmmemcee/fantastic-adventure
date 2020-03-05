/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
import com.tm.sighting.model.SuperHero;
import java.util.ArrayList;
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
public class SuperHeroDaoTest {
    SuperHeroDao dao;
    OrganizationDao orgDao;
    
    public SuperHeroDaoTest() {
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

        dao = ctx.getBean("SuperHeroDao", SuperHeroDao.class);
        orgDao = ctx.getBean("OrganizationDao",OrganizationDao.class);
        // delete all books
        List<SuperHero> heros = dao.getAllSuperHeros();
        for (SuperHero currentSuperHero : heros) {
            dao.deleteSuper(currentSuperHero.getSuperHeroId());
        }
        orgDao.getAllOrganizations().stream()
                .forEach(o -> orgDao.deleteOrganization(o.getOrganizationId()));
    }
    
    @After
    public void tearDown() {
    }

        /**
     * Test of addSuper and getSuper methods, of class SuperHeroDao.
     */
    @Test
    public void testAddGetSuper() {
        
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        dao.addSuper(hero);
        SuperHero heroFromDao = dao.getSuper(hero.getSuperHeroId());
        assertEquals(hero, heroFromDao);
    }
    /**
     * Test of getAllSuperHeros method, of class SuperHeroDao.
     */
    @Test
    public void testGetAllSuperHeros() {
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        List<SuperHero> supers = dao.getAllSuperHeros();
        assertEquals(supers.size(), 0);
        dao.addSuper(hero);
        supers = dao.getAllSuperHeros();
        assertEquals(supers.size(), 1);
        
        SuperHero hero2 = new SuperHero();
        hero2.setSuperHeroName("Batman");
        hero2.setDescription("richboi");
        hero2.setUsePowerForGood(true);
        dao.addSuper(hero2);
        supers = dao.getAllSuperHeros();
        assertEquals(supers.size(), 2);
        
    }

    /**
     * Test of updateSuper method, of class SuperHeroDao.
     */
    @Test
    public void testUpdateSuper() {
        
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        dao.addSuper(hero);
        SuperHero hero2 = dao.getSuper(hero.getSuperHeroId());
        assertEquals(hero, hero2);
        hero.setSuperHeroName("Batman");
        assertNotEquals(hero, dao.getSuper(hero.getSuperHeroId()));
        dao.updateSuper(hero);
        assertEquals(hero, dao.getSuper(hero.getSuperHeroId()));
    }

        /**
     * Test of removeSuper method, of class SuperHeroDao.
     */
    @Test
    public void testRemoveSuper() {
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        dao.addSuper(hero);
        SuperHero heroFromDao = dao.getSuper(hero.getSuperHeroId());
        assertEquals(hero, heroFromDao);
        dao.deleteSuper(hero.getSuperHeroId());
        assertNull(dao.getSuper(hero.getSuperHeroId()));
    }

    /**
     * Test of getAllSuperHerosByOrganization method, of class SuperHeroDao.
     */
    @Test
    public void testGetAllSuperHerosByOrganization() {
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        hero.setDescription("Kryptonite is his weakness, and he is an alien");
        hero.setUsePowerForGood(true);
        Organization org = new Organization();
        org.setName("Test Org");
        orgDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        hero.setOrganizations(orgs);
        
        List<SuperHero> heros = dao.getAllSuperHerosByOrganization(org.getOrganizationId());
        assertEquals(heros.size(), 0);
        dao.addSuper(hero);
        heros = dao.getAllSuperHerosByOrganization(org.getOrganizationId());
        assertEquals(heros.size(), 1);
    }


    /**
     * Test of addSuperHeroToOrganization method, of class SuperHeroDao.
     */
    @Test
    public void testAddSuperHeroToOrganization() {
    }

    /**
     * Test of findSuperHeroForSighting method, of class SuperHeroDao.
     */
    @Test
    public void testFindSuperHeroForSighting() {
    }
    
}
