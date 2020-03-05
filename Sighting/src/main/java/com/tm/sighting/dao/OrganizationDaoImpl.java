/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class OrganizationDaoImpl implements OrganizationDao{
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORGANIZATION_LOCATION = "insert into "
            + "organizationLocation (organizationId, locationId) values (?,?)";
    private static final String SQL_INSERT_ORGANIZATION = "insert into "
            + "organization (organizationName, description, phoneNumber) "
            + "values (?, ?, ?)";
    private static final String SQL_UPDATE_ORGANIZATION = "update organization set "
            + "organizationName = ?, description = ?, phoneNumber = ? where "
            + "organizationId = ?";
    private static final String SQL_DELETE_ORGANIZATION_LOCATION = "delete from "
            + "organizationLocation where organizationId = ?";
    private static final String SQL_DELETE_ORGANIZATION = "delete from organization "
            + "where organizationId = ?";
    private static final String SQL_SELECT_ORGANIZATION_BY_ID = "select * from "
            + "organization where organizationId = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATIONS = "select * from"
            + " organization";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID = "select "
            + "o.organizationId, o.organizationName, o.description, o.phoneNumber "
            + "from organization o join superHeroOrganization so on "
            + "o.organizationId = so.organizationId where so.superHeroId = ?";
    
    public void insertOrganizationLocation(Organization org) {
        final int id = org.getOrganizationId();
        if (org.getLocation()!=null) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_LOCATION, id,
                    org.getLocation().getLocationId());
    
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION, organization.getName(), 
                organization.getDescription(), organization.getPhone());
        organization.setOrganizationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Integer.class));
        insertOrganizationLocation(organization);
        return organization;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(long organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_LOCATION, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        int id = organization.getOrganizationId();
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, 
                organization.getName(), organization.getDescription(),
                organization.getPhone(), id);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_LOCATION, id);
        insertOrganizationLocation(organization);
    }

    @Override
    public Organization getOrganizationById(long Id) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_BY_ID, 
                new OrganizationMapper(), Id);
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Organization> getAllOrganizationsFromIdArray(String[] ids) {
        List<Organization> orgs;
        orgs = new ArrayList<>();
        Organization org;
        if (ids !=null) {
            for (int i=0; i < ids.length; i++) {
                org = getOrganizationById(Long.parseLong(ids[i]));
                orgs.add(org);
            }
        }
        return orgs;
    }
    
    @Override
    public List<Organization> getAllOrganizations(){
        List<Organization> orgs =  jdbcTemplate
                .query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        return orgs;
    }
    
    @Override
    public List<Organization> getAllOrganizationsForSuperHero(long heroId) {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID,  
                new OrganizationDaoImpl.OrganizationMapper(), heroId);
        return orgs;
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setName(rs.getString("organizationName"));
            org.setDescription(rs.getString("description"));
            org.setPhone(rs.getString("phoneNumber"));
            org.setOrganizationId(rs.getInt("organizationId"));
            return org;
        }
    }
    
}
