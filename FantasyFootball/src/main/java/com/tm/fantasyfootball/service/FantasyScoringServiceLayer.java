/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.service;

import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.ScoringScheme;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface FantasyScoringServiceLayer {
    ScoringScheme getScoring(int scoreId);
    List<ScoringScheme> getAllScoring();
    void addScoring(ScoringScheme score);
    Player getPlayerById(int playerId);
    List<Player> getAllPlayers();
    List<Player> getAllPlayersWithFantasyPoints(ScoringScheme scoring);
    List<Player> getAllPlayersByNameStarting(char initial);
}
