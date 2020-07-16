/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.ScoringScheme;
import com.tm.fantasyfootball.model.Season;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface ScoringDao {
    void addScoring(ScoringScheme score);
    List<ScoringScheme> getAllScoring();
    ScoringScheme getScoring(int scoreId);
    float findFantasyPoints(ScoringScheme scoring, Season season);
}
