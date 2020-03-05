/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
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
public class OrganizationDaoTest {
    OrganizationDao dao;
    LocationDao locDao;
    
    public OrganizationDaoTest() {
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

        dao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        locDao = ctx.getBean("LocationDao",LocationDao.class);
    
        dao.getAllOrganizations().stream().forEach(o -> dao.deleteOrganization(o.getOrganizationId()));
        locDao.getAllLocations().stream().forEach(l -> locDao.deleteLocation(l.getLocationId()));
    
    
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddGetOrganization() {
        
        Organization org = new Organization();
        org.setName("League of Doom");
        org.setDescription("Fun group");
        org.setPhone("911-555-3205");
        dao.addOrganization(org);

        assertEquals(org,dao.getOrganizationById(org.getOrganizationId()));

    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org = new Organization();
        org.setName("League of Doom");
        org.setDescription("Fun group");
        org.setPhone("911-555-3205");
        
        dao.addOrganization(org);
        Organization orgFromDao = dao.getOrganizationById(org.getOrganizationId());
        assertEquals(org,orgFromDao);
        dao.deleteOrganization(org.getOrganizationId());
        assertNull(dao.getOrganizationById(org.getOrganizationId()));

    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org = new Organization();
        org.setName("League of Doom");
        org.setDescription("Fun group");
        org.setPhone("911-555-3205");
        dao.addOrganization(org);
        
        org.setName("different");
        assertNotEquals(org, dao.getOrganizationById(org.getOrganizationId()));
        dao.updateOrganization(org);
        assertEquals(org, dao.getOrganizationById(org.getOrganizationId()));
    }

    /**
     * Test of getAllOrganizationsFromIdArray method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizationsFromIdArray() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        org.setName("League of Doom");
        org.setDescription("Fun group");
        org.setPhone("911-555-3205");
        assertEquals(0, dao.getAllOrganizations().size());
        dao.addOrganization(org);

        assertEquals(1, dao.getAllOrganizations().size());
    }

    /**
     * Test of getAllOrganizationsForSuperHero method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizationsForSuperHero() {
    }

    
}
