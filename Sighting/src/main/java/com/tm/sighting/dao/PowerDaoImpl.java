/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Power;
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
public class PowerDaoImpl implements PowerDao{
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_POWER = "insert into superPower "
            + "(powerName, description) values (?, ?)";
    private static final String SQL_UPDATE_POWER = "update superPower set "
            + "powerName = ?, description = ? where powerId = ?";
    private static final String SQL_DELETE_POWER = "delete from superPower where "
            + "powerId = ?";
    private static final String SQL_SELECT_ALL_POWERS = "select * from superPower";
    private static final String SQL_SELECT_POWER_BY_ID = "select * from superPower "
            + "where powerId = ?";
    private static final String SQL_SELECT_POWERS_BY_SUPERHERO_ID = "select "
            + "p.powerId, p.powerName, p.description from superPower p join superHeroPower sp on "
            + "p.powerId = sp.powerId where sp.superHeroId = ?";
    private static final String SQL_DELETE_SUPERHERO_POWER_BY_POWER_ID = "delete from "
            + "superHeroPower where powerId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER, power.getPowerName(), power.getDescription());
        long powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);
        power.setPowerId(powerId);
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER, power.getPowerName(),
                power.getDescription(), power.getPowerId());
    }

    @Override
    public Power getPowerById(long powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER_BY_ID, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }

    
    @Override
    public void deletePower(long powerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHERO_POWER_BY_POWER_ID, powerId);
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    public List<Power> findPowersForSuperHero(long heroId) {
        return jdbcTemplate.query(SQL_SELECT_POWERS_BY_SUPERHERO_ID, new PowerMapper(), heroId);
    }
    

    private static final class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();
            power.setDescription(rs.getString("description"));
            power.setPowerName(rs.getString("powerName"));
            power.setPowerId(rs.getLong("powerId"));
            return power;
        }
    }
    
}
