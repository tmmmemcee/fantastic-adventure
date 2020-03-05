/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Location;
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
public class LocationDaoImpl implements LocationDao{
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION = "insert into location "
            + "(longitude, latitude, locationName, address, description) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_LOCATION = "update location set "
            + "longitude = ?, latitude = ?, locationName = ?, address = ?, "
            + "description = ? where locationId = ?";
    private static final String SQL_DELETE_LOCATION = "delete from location where "
            + "locationId = ?";
    private static final String SQL_SELECT_LOCATION_BY_ID = "select * from "
            + "location where locationId = ?";
    private static final String SQL_SELECT_ALL_LOCATIONS = "select * from location";
    private static final String SQL_DELETE_LOCATION_ORGANIZATION = "delete from "
            + "organizationLocation where locationId = ?";
    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION_ID = "select "
            + "l.locationId, l.locationName, l.longitude, l.latitude, l.address, "
            + "l.description from location l join organizationLocation ol on "
            + "l.locationId = ol.locationId where ol.organizationId = ?";
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID = "select * from "
            + "location l join superHeroLocation sl on l.locationId = sl.locationId "
            + "where sl.superHeroLocationId = ?";
    private static final String SQL_DELETE_SUPERHERO_LOCATION_BY_LOCATION_ID = 
            "delete from superHeroLocation where locationId = ?";
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, 
                location.getLongitude(), 
                location.getLatitude(), 
                location.getName(), 
                location.getAddress(),
                location.getDescription());
        int locationId = (jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Integer.class));
        location.setLocationId(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, location.getLongitude(), 
                location.getLatitude(), location.getName(), location.getAddress(),
                location.getDescription(), location.getLocationId());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(long locationId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_LOCATION_BY_LOCATION_ID, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION_ORGANIZATION, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public Location getLocationById(long locationId) {
        try {
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
            return loc;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Location> getAllLocations(){
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }
    @Override
    public Location findLocationForOrganization(long orgId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION_ID, 
                    new LocationMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    

    @Override
    public Location findLocationForSighting(long sightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingId);
        }  catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    private static final class LocationMapper implements RowMapper<Location> { 
        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setName(rs.getString("locationName"));
            location.setAddress(rs.getString("address"));
            location.setDescription(rs.getString("description"));
            location.setLongitude(rs.getString("longitude"));
            location.setLatitude(rs.getString("latitude"));
            location.setLocationId(rs.getInt("locationId"));
            return location;
        }
    }
    
}
