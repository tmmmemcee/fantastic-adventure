/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Power;
import com.tm.sighting.model.SuperHero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tmmmemcee
 */
public class SuperHeroDaoJdbcTemplateImpl implements SuperHeroDao{
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_SUPERHERO_POWER = "insert into "
            + "superHeroPower (superHeroId, powerId) values (?, ?)";
    private static final String SQL_INSERT_SUPERHERO = "insert into superhero "
            + "(superHeroName,description,usePowerForGood) values (?, ?, ?)";
    private static final String SQL_UPDATE_SUPERHERO = "update superhero set "
            + "superHeroName = ?, description = ?, usePowerForGood = ? "
            + "where superheroId = ?";
    private static final String SQL_DELETE_SUPERHERO = "delete from superhero "
            + "where superheroId = ?";
    private static final String SQL_DELETE_SUPERHERO_POWER = "delete from "
            + "superHeroPower where superHeroId = ?";
    private static final String SQL_DELETE_SUPERHERO_ORGANIZATION = "delete "
            + "from superHeroOrganization where superHeroId = ?";
    private static final String SQL_DELETE_SUPERHERO_LOCATION = "delete from "
            + "superHeroLocation where superHeroId = ?";
    private static final String SQL_DELETE_SUPERHERO_PICTURE = "delete from photo "
            + "where photoId = ?";
    private static final String SQL_SELECT_SUPERHERO = "select * from superhero"
            + " where superheroId = ?";
    private static final String SQL_SELECT_ALL_SUPERHEROS = "select * from "
            + "superhero";
    private static final String SQL_SELECT_SUPERHEROS_BY_ORDGANIZATION = 
            "select s.superheroId, s.superHeroName, s.description, "
            + "s.usePowerForGood from superhero s join superHeroOrganization so "
            + "on s.superheroId = so.superHeroId where so.organizationId = ?";
    private static final String SQL_INSERT_SUPERHERO_ORGANIZATION = "insert into "
            + "superHeroOrganization (superHeroId, organizationId) values "
            + "(?, ?)";
    private static final String SQL_SELECT_SUPERHERO_BY_SIGHTING_ID = "select * "
            + "from superhero s join superHeroLocation sl on s.superheroId = sl.superHeroId "
            + "where sl.superHeroLocationId = ?";
    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHero addSuper(SuperHero superHero) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO, superHero.getSuperHeroName(),
                superHero.getDescription(), superHero.getUsePowerForGood());
        superHero.setSuperHeroId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Integer.class));
        insertSuperHeroPower(superHero);
        insertSuperHeroOrganization(superHero);
        return superHero;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuper(long superHeroId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_POWER, superHeroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_LOCATION,superHeroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_ORGANIZATION, superHeroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_PICTURE, superHeroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO, superHeroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuper(SuperHero superHero) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO, superHero.getSuperHeroName(), 
                superHero.getDescription(), superHero.getUsePowerForGood(), 
                superHero.getSuperHeroId());
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_POWER, superHero.getSuperHeroId());
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_ORGANIZATION, superHero.getSuperHeroId());
        insertSuperHeroPower(superHero);
        insertSuperHeroOrganization(superHero);
    }
    
    @Override
    public SuperHero getSuper(long superHeroId) {
        try {
            SuperHero hero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, new SuperHeroMapper(), superHeroId);
//            hero.setSuperPowers(powerDao.findPowersForSuperHero(hero));
//            hero.setOrganizations(orgDao.findOrganizationsForSuperHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<SuperHero> getAllSuperHeros() {
        List<SuperHero> heros = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROS, new SuperHeroMapper());
        return heros;
    }

    public void insertSuperHeroPower(SuperHero hero) {
        final long id = hero.getSuperHeroId();
        List<Power> powers = hero.getSuperPowers();
        if (powers!=null) {
            powers.stream().forEach((Power p) -> jdbcTemplate
                .update(SQL_INSERT_SUPERHERO_POWER, hero.getSuperHeroId(), p.getPowerId()));
        }        
    }
    public void insertSuperHeroOrganization(SuperHero hero) {
        final long id = hero.getSuperHeroId();
        List<Organization> orgs = hero.getOrganizations();
        if (orgs!=null ) {
            orgs.stream().forEach((Organization o) -> jdbcTemplate
                .update(SQL_INSERT_SUPERHERO_ORGANIZATION, 
                        hero.getSuperHeroId(), o.getOrganizationId()));
        }
    }

    @Override
    public List<SuperHero> getAllSuperHerosByOrganization(long organizationId) {
        List<SuperHero> heros =  jdbcTemplate.query(SQL_SELECT_SUPERHEROS_BY_ORDGANIZATION, 
                new SuperHeroMapper(), organizationId);
        return heros;
    }

    @Override
    public SuperHero getSuperHeroFromSighting(long sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO_BY_SIGHTING_ID, new SuperHeroMapper(), sightingId);
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class SuperHeroMapper implements RowMapper<SuperHero> { 
        
        @Override
        public SuperHero mapRow(ResultSet rs, int i) throws SQLException {
            SuperHero hero = new SuperHero();
            hero.setSuperHeroName(rs.getString("superHeroName"));
            hero.setDescription(rs.getString("description"));
            hero.setUsePowerForGood(rs.getBoolean("usePowerForGood"));
            hero.setSuperHeroId(rs.getInt("superheroId"));
            return hero;
        }
    }
}
