/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Picture;
import com.tm.sighting.model.SuperHero;
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
public class PictureDaoTest {
    PictureDao dao;
    SuperHeroDao superDao;
    
    public PictureDaoTest() {
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

        dao = ctx.getBean("PictureDao", PictureDao.class);
        superDao = ctx.getBean("SuperHeroDao", SuperHeroDao.class);
        dao.getAllPictures().stream().forEach(p -> dao.deletePicture(p.getPictureId()));
        List<SuperHero> heros = superDao.getAllSuperHeros();
        for (SuperHero currentSuperHero : heros) {
            superDao.deleteSuper(currentSuperHero.getSuperHeroId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPicture method, of class PictureDao.
     */
    @Test
    public void testAddPicture() {
        Picture pic = new Picture();
        pic.setTitle("title");
        pic.setFilename("filename");
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        pic.setPictureId(hero.getSuperHeroId());
        dao.addPicture(pic);
        assertEquals(pic, dao.getPictureById(hero.getSuperHeroId()));
        
    }

    /**
     * Test of deletePicture method, of class PictureDao.
     */
    @Test
    public void testDeletePicture() {
        Picture pic = new Picture();
        pic.setTitle("title");
        pic.setFilename("filename");
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        pic.setPictureId(hero.getSuperHeroId());
        dao.addPicture(pic);
        assertNotNull(dao.getPictureById(hero.getSuperHeroId()));
        dao.deletePicture(hero.getSuperHeroId());
        assertNull(dao.getPictureById(hero.getSuperHeroId()));
    }

    /**
     * Test of getAllPictures method, of class PictureDao.
     */
    @Test
    public void testGetAllPictures() {
        Picture pic = new Picture();
        pic.setTitle("title");
        pic.setFilename("filename");
        SuperHero hero = new SuperHero();
        hero.setSuperHeroName("Superman");
        superDao.addSuper(hero);
        pic.setPictureId(hero.getSuperHeroId());
        dao.addPicture(pic);
        assertEquals(1, dao.getAllPictures().size());
    }

}
