/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.model;

import java.util.Arrays;

/**
 *
 * @author tmmmemcee
 */
public class Season {
    long playerId;
    float fantasyTotal;
    long[] teamId;
    int age;
    int experience;
    int year;
    int games;
    int gameStarted;
    int completions;
    int passAttempts;
    int passYards;
    int passTds;
    int inters;
    int rushAttempts;
    int rushTds;
    int rushYards;
    float yardPerAttempt;
    int targets;
    int receptions;
    int recYards;
    int recTds;
    float yardPerCatch;
    int fumbles;
    int fumblesLost;
    int twoPointConverstions;

    public long getPlayerId() {
        
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public float getFantasyTotal() {
        return fantasyTotal;
    }

    public void setFantasyTotal(float fantasyTotal) {
        this.fantasyTotal = fantasyTotal;
    }
    
    public long[] getTeamId() {
        return teamId;
    }

    public void setTeamId(long[] teamId) {
        this.teamId = teamId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(int gameStarted) {
        this.gameStarted = gameStarted;
    }

    public int getCompletions() {
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public int getPassAttempts() {
        return passAttempts;
    }

    public void setPassAttempts(int passAttempts) {
        this.passAttempts = passAttempts;
    }

    public int getPassYards() {
        return passYards;
    }

    public void setPassYards(int passYards) {
        this.passYards = passYards;
    }

    public int getPassTds() {
        return passTds;
    }

    public void setPassTds(int passTds) {
        this.passTds = passTds;
    }

    public int getInters() {
        return inters;
    }

    public void setInters(int inters) {
        this.inters = inters;
    }

    public int getRushAttempts() {
        return rushAttempts;
    }

    public void setRushAttempts(int rushAttempts) {
        this.rushAttempts = rushAttempts;
    }

    public int getRushTds() {
        return rushTds;
    }

    public void setRushTds(int rushTds) {
        this.rushTds = rushTds;
    }

    public int getRushYards() {
        return rushYards;
    }

    public void setRushYards(int rushYards) {
        this.rushYards = rushYards;
    }

    public float getYardPerAttempt() {
        return yardPerAttempt;
    }

    public void setYardPerAttempt(float yardPerAttempt) {
        this.yardPerAttempt = yardPerAttempt;
    }

    public int getTargets() {
        return targets;
    }

    public void setTargets(int targets) {
        this.targets = targets;
    }

    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    public int getRecYards() {
        return recYards;
    }

    public void setRecYards(int recYards) {
        this.recYards = recYards;
    }

    public int getRecTds() {
        return recTds;
    }

    public void setRecTds(int recTds) {
        this.recTds = recTds;
    }

    public float getYardPerCatch() {
        return yardPerCatch;
    }

    public void setYardPerCatch(float yardPerCatch) {
        this.yardPerCatch = yardPerCatch;
    }

    public int getFumbles() {
        return fumbles;
    }

    public void setFumbles(int fumbles) {
        this.fumbles = fumbles;
    }

    public int getFumblesLost() {
        return fumblesLost;
    }

    public void setFumblesLost(int fumblesLost) {
        this.fumblesLost = fumblesLost;
    }

    public int getTwoPointConverstions() {
        return twoPointConverstions;
    }

    public void setTwoPointConverstions(int twoPointConverstions) {
        this.twoPointConverstions = twoPointConverstions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.playerId ^ (this.playerId >>> 32));
        hash = 79 * hash + Arrays.hashCode(this.teamId);
        hash = 79 * hash + this.age;
        hash = 79 * hash + this.experience;
        hash = 79 * hash + this.year;
        hash = 79 * hash + this.games;
        hash = 79 * hash + this.gameStarted;
        hash = 79 * hash + this.completions;
        hash = 79 * hash + this.passAttempts;
        hash = 79 * hash + this.passYards;
        hash = 79 * hash + this.passTds;
        hash = 79 * hash + this.inters;
        hash = 79 * hash + this.rushAttempts;
        hash = 79 * hash + this.rushTds;
        hash = 79 * hash + this.rushYards;
        hash = 79 * hash + Float.floatToIntBits(this.yardPerAttempt);
        hash = 79 * hash + this.targets;
        hash = 79 * hash + this.receptions;
        hash = 79 * hash + this.recYards;
        hash = 79 * hash + this.recTds;
        hash = 79 * hash + Float.floatToIntBits(this.yardPerCatch);
        hash = 79 * hash + this.fumbles;
        hash = 79 * hash + this.fumblesLost;
        hash = 79 * hash + this.twoPointConverstions;
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
        final Season other = (Season) obj;
        if (this.playerId != other.playerId) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.experience != other.experience) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.games != other.games) {
            return false;
        }
        if (this.gameStarted != other.gameStarted) {
            return false;
        }
        if (this.completions != other.completions) {
            return false;
        }
        if (this.passAttempts != other.passAttempts) {
            return false;
        }
        if (this.passYards != other.passYards) {
            return false;
        }
        if (this.passTds != other.passTds) {
            return false;
        }
        if (this.inters != other.inters) {
            return false;
        }
        if (this.rushAttempts != other.rushAttempts) {
            return false;
        }
        if (this.rushTds != other.rushTds) {
            return false;
        }
        if (this.rushYards != other.rushYards) {
            return false;
        }
        if (Float.floatToIntBits(this.yardPerAttempt) != Float.floatToIntBits(other.yardPerAttempt)) {
            return false;
        }
        if (this.targets != other.targets) {
            return false;
        }
        if (this.receptions != other.receptions) {
            return false;
        }
        if (this.recYards != other.recYards) {
            return false;
        }
        if (this.recTds != other.recTds) {
            return false;
        }
        if (Float.floatToIntBits(this.yardPerCatch) != Float.floatToIntBits(other.yardPerCatch)) {
            return false;
        }
        if (this.fumbles != other.fumbles) {
            return false;
        }
        if (this.fumblesLost != other.fumblesLost) {
            return false;
        }
        if (this.twoPointConverstions != other.twoPointConverstions) {
            return false;
        }
        if (!Arrays.equals(this.teamId, other.teamId)) {
            return false;
        }
        return true;
    }

}
