/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.service;

import com.tm.fantasyfootball.dao.PlayerDao;
import com.tm.fantasyfootball.dao.ScoringDao;
import com.tm.fantasyfootball.dao.SeasonDao;
import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.ScoringScheme;
import com.tm.fantasyfootball.model.Season;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author tmmmemcee
 */
public class FantasyScoringServiceLayerImpl implements FantasyScoringServiceLayer {
    ScoringDao scoringDao;
    PlayerDao playerDao;
    SeasonDao seasonDao;

    @Inject
    public FantasyScoringServiceLayerImpl(ScoringDao scoringDao, PlayerDao playerDao, SeasonDao seasonDao) {
        this.scoringDao = scoringDao;
        this.playerDao = playerDao;
        this.seasonDao = seasonDao;
    }
    
    @Override
    public void addScoring(ScoringScheme score) {
        scoringDao.addScoring(score);
    }

    @Override
    public ScoringScheme getScoring(int scoreId){
        return scoringDao.getScoring(scoreId);
    }
    
    @Override
    public Player getPlayerById(int playerId) {
        Player player = playerDao.getPlayerById(playerId);
        List<Season> seasons = seasonDao.getAllSeasonsForPlayer(player);
        for (Season season : seasons) {
            player.addStats(season);
        }
        return player;
    }
    
    @Override
    public List<ScoringScheme> getAllScoring() {
        return scoringDao.getAllScoring();
    }
    
    
    @Override
    public List<Player> getAllPlayersWithFantasyPoints(ScoringScheme scoring) {
        List<Player> players= playerDao.getAllPlayers();
        for (Player player:players) {
            List<Season> seasons = seasonDao.getAllSeasonsForPlayer(player);
            for (Season season: seasons) {
                season.setFantasyTotal(scoringDao.findFantasyPoints(scoring, season));
                player.addStats(season);
            }
        }
        return players;
    }
    
    @Override
    public List<Player> getAllPlayersByNameStarting(char initial) {
        List<Player> players = playerDao.getAllPlayersByNameStartingWith(initial);
        return players;
    }
    
    @Override
    public List<Player> getAllPlayers() {
        List<Player> players= playerDao.getAllPlayers();
        for (Player player:players) {
            List<Season> seasons = seasonDao.getAllSeasonsForPlayer(player);
            for (Season season: seasons)
            player.addStats(season);
        }
        return players;
    }
}
