/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tmmmemcee
 */
public class Player {
    long playerId;
    String name;
    int positionId;
    List<Season> stats = new ArrayList<>();
    
    
    public List<Season> getStats() {
        return stats;
    }
    public void addStats(Season season) {
        this.stats.add(season);
    }
    
    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.playerId ^ (this.playerId >>> 32));
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.positionId;
        hash = 59 * hash + Objects.hashCode(this.stats);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.playerId != other.playerId) {
            return false;
        }
        if (this.positionId != other.positionId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.stats, other.stats)) {
            return false;
        }
        return true;
    }
    
}
