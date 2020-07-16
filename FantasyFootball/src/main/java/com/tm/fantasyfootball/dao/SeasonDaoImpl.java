/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.Season;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tmmmemcee
 */
public class SeasonDaoImpl implements SeasonDao {
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_DELETE_ALL_SEASONSTATS_FOR_PLAYER = "delete * from "
            + "seasonStats where playerId = ?";
    private static final String SQL_INSERT_SEASONSTATS_FOR_PLAYER = "insert into seasonStats"
            + "(playerId, season, age, experience,  games, gamesStarted,"
            + " passAttempts, completions, passYards, passTds, "
            + "ints, rushAttempts, rushYards, rushTds, yardsPerAttempt,"
            + "receptions, targets, recYards, recTds, yardsPerCatch, "
            + "fumbles, fumblesLost, twoPointConversion) "
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_SEASONSTATS_FROM_PLAYER_AND_YEAR = "select "
            + "* from seasonStats where playerId =  ? and season = ?";
    private static final String SQL_SELECT_SEASONSTATS_FOR_PLAYER = "select "
            + "* from seasonStats where playerId =  ?";
    
    @Override
    public void addSeason(Player player, Season season) {
        jdbcTemplate.update(SQL_INSERT_SEASONSTATS_FOR_PLAYER, player.getPlayerId(), 
                season.getYear(), season.getAge(), season.getExperience(), 
                season.getGames(), season.getGameStarted(), season.getPassAttempts(), 
                season.getCompletions(), season.getPassYards(), season.getPassTds(), 
                season.getInters(), season.getRushAttempts(), season.getRushYards(),
                season.getRushTds(), season.getYardPerAttempt(), season.getReceptions(),
                season.getTargets(), season.getRecYards(), season.getRecTds(), 
                season.getYardPerCatch(), season.getFumbles(), season.getFumblesLost(), 
                season.getTwoPointConverstions());
    }
    @Override
    public Season getSeason(long playerId, int season) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SEASONSTATS_FROM_PLAYER_AND_YEAR, new SeasonMapper(), playerId, season);
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    } 
    @Override
    public void deleteSeason(Season season) {
        jdbcTemplate.update(SQL_DELETE_ALL_SEASONSTATS_FOR_PLAYER);
    }
    
    @Override
    public List<Season> getAllSeasonsForPlayer(Player player) {
        return jdbcTemplate.query(SQL_SELECT_SEASONSTATS_FOR_PLAYER, new SeasonMapper(), player.getPlayerId());
    }
    
    private static final class SeasonMapper implements RowMapper<Season> { 
        
        @Override
        public Season mapRow(ResultSet rs, int i) throws SQLException {
        Season season = new Season();
        season.setPlayerId(rs.getLong("playerId"));
        season.setYear(rs.getInt("season"));
        season.setAge(rs.getInt("age"));
        season.setExperience(rs.getInt("experience"));
        season.setGameStarted(rs.getInt("gamesStarted"));
        season.setGames(rs.getInt("games"));
        season.setPassAttempts(rs.getInt("passAttempts"));
        season.setCompletions(rs.getInt("completions"));
        season.setPassYards(rs.getInt("passYards"));
        season.setPassTds(rs.getInt("passTds"));
        season.setInters(rs.getInt("ints"));
        season.setRushAttempts(rs.getInt("rushAttempts"));
        season.setRushYards(rs.getInt("rushYards"));
        season.setRushTds(rs.getInt("rushTds"));
        season.setYardPerAttempt(rs.getFloat("yardsPerAttempt"));
        season.setReceptions(rs.getInt("receptions"));
        season.setRecYards(rs.getInt("recYards"));
        season.setRecTds(rs.getInt("RecTds"));
        season.setYardPerCatch(rs.getFloat("yardsPerCatch"));
        season.setFumbles(rs.getInt("fumbles"));
//        season.setFumblesLost(rs.getInt("fumblesLost"));
//        season.setTwoPointConverstions(rs.getInt("twoPointConversion"));
        return season;
        }
    }
}
