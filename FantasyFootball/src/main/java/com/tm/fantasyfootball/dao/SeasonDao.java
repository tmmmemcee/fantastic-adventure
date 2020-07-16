/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.Season;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface SeasonDao {
    void addSeason(Player player, Season season);
    Season getSeason(long playerId, int season);
    List<Season> getAllSeasonsForPlayer(Player player);
    void deleteSeason(Season season);
}
