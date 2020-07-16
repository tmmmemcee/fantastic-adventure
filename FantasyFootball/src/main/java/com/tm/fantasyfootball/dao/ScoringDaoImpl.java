/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.ScoringScheme;
import com.tm.fantasyfootball.model.Season;
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
public class ScoringDaoImpl implements ScoringDao {
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_SCORING = "insert into scoring (numberOfTeams, "
            + "numberOfQbs, numberOfRbs,  numberOfWrs, numberOfTes, numberOfRWT, "
            + "numberOfRW, numberOfWT, passYardPerPoint, pointPerPassTd, pointPerInt, "
            + "rushYardPerPoint, pointPerRushTd, pointPerFumble, recYardPerPoint, "
            + "pointPerRecTd, pointPerReception) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_SCORING_BY_ID = "select * from scoring where scoringId = ?";
    private static final String SQL_SELECT_ALL_SCORING = "select * from scoring";
    
    @Override
    public ScoringScheme getScoring(int scoreId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SCORING_BY_ID, new ScoringMapper(), scoreId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<ScoringScheme> getAllScoring() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SCORING, new ScoringMapper());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addScoring(ScoringScheme score) {
        jdbcTemplate.update(SQL_INSERT_SCORING, score.getNumTeams(), score.getNumQBs(),
                score.getNumRBs(), score.getNumWRs(), score.getNumTEs(), 
                score.getNumWRT(), score.getNumWR(), score.getNumWT(), score.getPassYdPrPoint(),
                score.getPointPrPsTd(), score.getPointPrInt(), score.getRushYdPrPoint(), 
                score.getPointPrRsTd(), score.getPointPrFmbl(), score.getRecYdPrPoint(), 
                score.getPointPrPsTd(), score.getPointPrRec());
        long scoreId = (jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                Long.class));
        score.setScoreId(scoreId);
    }

    @Override
    public float findFantasyPoints(ScoringScheme scoring, Season season) {
        float total = 0;
        total += season.getPassYards()/scoring.getPassYdPrPoint();
        total += season.getPassTds()*scoring.getPointPrPsTd();
        total += season.getInters()*scoring.getPointPrInt();
        total += season.getRushYards()/scoring.getRushYdPrPoint();
        total += season.getRushTds()*scoring.getPointPrRsTd();
        total += season.getFumbles()*scoring.getPointPrFmbl();
        total += season.getRecYards()/scoring.getRecYdPrPoint();
        total += season.getRecTds()*scoring.getPointPrRcTd();
        total += season.getReceptions()*scoring.getPointPrRec();
        return total;
    }
    private static final class ScoringMapper implements RowMapper<ScoringScheme> { 
        
        @Override
        public ScoringScheme mapRow(ResultSet rs, int i) throws SQLException {
            ScoringScheme scoring = new ScoringScheme();
            scoring.setScoreId(rs.getInt("scoringId"));
            scoring.setNumTeams(rs.getInt("numberOfTeams"));
            scoring.setNumQBs(rs.getInt("numberOfQbs"));
            scoring.setNumRBs(rs.getInt("numberOfRbs"));
            scoring.setNumWRs(rs.getInt("numberOfWrs"));
            scoring.setNumTEs(rs.getInt("numberOfTes"));
            scoring.setNumWRT(rs.getInt("numberOfRWT"));
            scoring.setNumWR(rs.getInt("numberOfRW"));
            scoring.setNumWT(rs.getInt("numberOfWT"));
            scoring.setPassYdPrPoint(rs.getInt("passYardPerPoint"));
            scoring.setPointPrPsTd(rs.getInt("pointPerPassTd"));
            scoring.setPointPrInt(rs.getInt("pointPerInt"));
            scoring.setRushYdPrPoint(rs.getInt("rushYardPerPoint"));
            scoring.setPointPrRsTd(rs.getInt("pointPerRushTd"));
            scoring.setPointPrFmbl(rs.getInt("pointPerFumble"));
            scoring.setRecYdPrPoint(rs.getInt("recYardPerPoint"));
            scoring.setPointPrRcTd(rs.getInt("pointPerRecTd"));
            scoring.setPointPrRec(rs.getInt("pointPerReception"));
            return scoring;
            
        }
        
    }

}
