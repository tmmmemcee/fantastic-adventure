/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Power;
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
public class PowerDaoTest {
PowerDao dao;

    public PowerDaoTest() {
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

        dao = ctx.getBean("PowerDao", PowerDao.class);
        dao.getAllPowers().stream().forEach(p -> dao.deletePower(p.getPowerId()));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPower method, of class PowerDao.
     */
    @Test
    public void testAddPower() {
        Power power = new Power();
        power.setPowerName("superpower");
        dao.addPower(power);
        Power power2 = dao.getPowerById(power.getPowerId());
        assertEquals(power, power2);
    }

    /**
     * Test of updatePower method, of class PowerDao.
     */
    @Test
    public void testUpdatePower() {
        Power power = new Power();
        power.setPowerName("superpower");
        dao.addPower(power);
        assertEquals(power, dao.getPowerById(power.getPowerId()));
        power.setPowerName("different superPower");
        dao.updatePower(power);
        assertEquals(power, dao.getPowerById(power.getPowerId()));
    }

    /**
     * Test of getAllPowersFromIdArray method, of class PowerDao.
     */
    @Test
    public void testGetAllPowersFromIdArray() {
    }

    /**
     * Test of getAllPowers method, of class PowerDao.
     */
    @Test
    public void testGetAllPowers() {
        Power power = new Power();
        power.setPowerName("superpower");
        dao.addPower(power);
        assertEquals(1, dao.getAllPowers().size());
        
        
    }

    /**
     * Test of deletePower method, of class PowerDao.
     */
    @Test
    public void testDeletePower() {
        Power power = new Power();
        power.setPowerName("superpower");
        dao.addPower(power);
        Power power2 = dao.getPowerById(power.getPowerId());
        assertEquals(power, power2);
        dao.deletePower(power.getPowerId());
        assertNull(dao.getPowerById(power.getPowerId()));
    }

    /**
     * Test of findPowersForSuperHero method, of class PowerDao.
     */
    @Test
    public void testFindPowersForSuperHero() {
    }

    
}
