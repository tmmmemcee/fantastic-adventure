/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dao;

import com.tm.fantasyfootball.model.Player;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface PlayerDao {
    void addPlayer(Player player);
    Player getPlayerByName(String name);
    Player getPlayerById(int playerId);
    List<Player> getAllPlayers();
    List<Player> getAllPlayersByNameStartingWith(char initial);
}
