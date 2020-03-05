/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Sighting;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoImpl implements SightingDao{

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String SQL_SELECT_ALL_SIGHTINGS = "select * from "
            + "superHeroLocation";
    private static final String SQL_INSERT_SUPERHERO_LOCATION = "insert into "
        + "superHeroLocation (superHeroId, locationId, dateOfSighting) "
        + "values (?, ?, ?)";
    private static final String SQL_DELETE_SIGHTING = "delete from superHeroLocation "
            + "where superHeroLocationId = ?";
    private static final String SQL_SELECT_SIGHTING_BY_ID = "select * from superHeroLocation "
            + "where superHeroLocationId = ?";
    private static final String SQL_UPDATE_SIGHTING = "update superHeroLocation set "
            + "superHeroId = ?, locationId = ?, dateOfSighting = ? where "
            + "superHeroLocationId = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION = "select "
            + "* from superHeroLocation where locationid = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE = "select "
            + "* from superHeroLocation where dateOfSighting = ?";
    private static final String SQL_SELECT_ALL_SIGHITNGS_BY_SUPERHERO = "select "
            + "* from superHeroLocation where superHeroId = ?";
    private static final String SQL_SELECT_MOST_RECENT_SIGHITNGS = "select * from "
            + "superHeroLocation order by dateOfSighting desc";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO_LOCATION,
                sighting.getSuperHero().getSuperHeroId(),
                sighting.getLocation().getLocationId(),
                Date.valueOf(sighting.getDate()));
        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Integer.class));
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public void deleteSighting(long id) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getSuperHero().getSuperHeroId(),
                sighting.getLocation().getLocationId(),
                Date.valueOf(sighting.getDate()), sighting.getSightingId());
    }

    @Override
    public Sighting getSighting(long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightingsBySuperHero(long superHeroId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHITNGS_BY_SUPERHERO, new SightingMapper(), superHeroId);
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(long locationId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_LOCATION, 
                new SightingMapper(), locationId);
    }

    @Override
    public List<Sighting> getAllSightingsByDate(Date date) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_BY_DATE, 
                new SightingMapper(), date);
    }

    @Override
    public List<Sighting> getMostRecentSightings() {
        return jdbcTemplate.query(SQL_SELECT_MOST_RECENT_SIGHITNGS, new SightingMapper());
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> { 
        
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getLong("superHeroLocationId"));
            sighting.setDate(LocalDate.parse(rs.getString("dateOfSighting")));
            return sighting;
        }
    }

}
