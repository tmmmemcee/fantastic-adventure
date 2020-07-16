/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.QB;
import com.tm.fantasyfootball.model.RB;
import com.tm.fantasyfootball.model.TE;
import com.tm.fantasyfootball.model.WR;
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
public class PlayerDaoImpl implements PlayerDao {
    
    private JdbcTemplate jdbcTemplate;
    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_PLAYER = "insert into player "
            + "(playerName, positionId) values (?,?)";
    private static final String SQL_DELETE_PLAYER = "delete from player where playerId = ?";
    private static final String SQL_SELECT_PLAYER_BY_NAME = "select * from "
            + "player where playerName = ?";
    private static final String SQL_SELECT_ALL_PLAYER = "select * from player";
    private static final String SQL_DELETE_SEASONSTATS_FOR_PLAYER = "delete * from "
            + "seasonStats where playerId = ?";
    private static final String SQL_SELECT_PLAYER_BY_ID = "select * from "
            + "player where playerId = ?";
    private static final String SQL_SELECT_ALL_PLAYERS_BY_NAME_STARTING_WITH = "select * from "
            + "player where playerName like ?";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPlayer(Player player) {
        jdbcTemplate.update(SQL_INSERT_PLAYER, player.getName(), player.getPositionId());
        long playerId = (jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Long.class));
        player.setPlayerId(playerId);
    }
    
    @Override    
    public List<Player> getAllPlayers(){
        List<Player> players = jdbcTemplate.query(SQL_SELECT_ALL_PLAYER, new PlayerMapper());
        return players;
    }
    
    @Override
    public List<Player> getAllPlayersByNameStartingWith(char initial) {
        String param = Character.toString(initial)+'%';
        List<Player> players = jdbcTemplate.query(SQL_SELECT_ALL_PLAYERS_BY_NAME_STARTING_WITH, new PlayerMapper(), Character.toString(initial)+'%');
        return players;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePlayer(Player player) {
        jdbcTemplate.update(SQL_DELETE_SEASONSTATS_FOR_PLAYER, player.getPlayerId());
        jdbcTemplate.update(SQL_DELETE_PLAYER, player.getPlayerId());
    }
    
    @Override
    public Player getPlayerByName(String name) {
//        if (name.equals("TravisKelce")) {
//            try {
//                Player player = jdbcTemplate.queryForObject(SQL_SELECT_PLAYER_BY_NAME, new PlayerMapper(), name); 
//                return player;
//            }  catch (EmptyResultDataAccessException ex) {
//                return null;
//            }
//        }
        try {
            Player player = jdbcTemplate.queryForObject(SQL_SELECT_PLAYER_BY_NAME, new PlayerMapper(), name); 
            return player;
        }  catch (EmptyResultDataAccessException ex) {
            return null;
        }
            
    }
    
    @Override
    public Player getPlayerById(int playerId) {
        try {
            Player player = jdbcTemplate.queryForObject(SQL_SELECT_PLAYER_BY_ID, new PlayerMapper(), playerId); 
             
            return player;
        }  catch (EmptyResultDataAccessException ex) {
            return null;
        }
            
    }
    
    private static final class PlayerMapper implements RowMapper<Player> { 
        
        @Override
        public Player mapRow(ResultSet rs, int i) throws SQLException {
            Player player = null;
            if (rs.getInt("positionId")==1) {
                player = new QB();
                player.setPlayerId(rs.getLong("playerId"));
                player.setName(rs.getString("playerName"));
            }
            if (rs.getInt("positionId")==2) {
                player = new RB();
                player.setPlayerId(rs.getLong("playerId"));
                player.setName(rs.getString("playerName"));
            }
            if (rs.getInt("positionId")==3) {
                player = new WR();
                player.setPlayerId(rs.getLong("playerId"));
                player.setName(rs.getString("playerName"));            
            }
            
            if (rs.getInt("positionId")==4) {
                player = new TE();
                player.setPlayerId(rs.getLong("playerId"));
                player.setName(rs.getString("playerName"));            
            }
            return player;
        }
    }
}
